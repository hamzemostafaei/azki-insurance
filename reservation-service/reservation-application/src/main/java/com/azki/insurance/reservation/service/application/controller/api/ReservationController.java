package com.azki.insurance.reservation.service.application.controller.api;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.domain.api.input.CommandHandler;
import com.azki.insurance.domain.api.input.QueryHandler;
import com.azki.insurance.domain.api.query.PaginatedQueryResult;
import com.azki.insurance.reservation.service.application.api.data.reservation.*;
import com.azki.insurance.reservation.service.domain.api.command.DeleteReservedSlotCommand;
import com.azki.insurance.reservation.service.domain.api.command.ReserveNearestAvailableSlotCommand;
import com.azki.insurance.reservation.service.domain.api.command.ReserveSlotCommand;
import com.azki.insurance.reservation.service.domain.api.command.ReserveSlotsCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.query.GetAvailableSlotsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CommandHandler<ReserveSlotsCommand, CommandResult<AvailableSlotsDTO>> createReservationHandler;
    private final CommandHandler<ReserveSlotCommand, CommandResult<Void>> reserveSlotCommandHandler;
    private final QueryHandler<GetAvailableSlotsQuery, PaginatedQueryResult<AvailableSlotsDTO>> getAvailableSlotsQueryHandler;
    private final CommandHandler<ReserveNearestAvailableSlotCommand, CommandResult<AvailableSlotsDTO>> reserveNearestSlotHandler;
    private final CommandHandler<DeleteReservedSlotCommand, CommandResult<Void>> deleteReservedSlotHandler;

    @PostMapping
    public ResponseEntity<CreateReservationEdgeResponseDTO> reserve(@Valid @RequestBody CreateReservationEdgeRequestDTO edgeRequest) {
        CreateReservationEdgeResponseDTO response = new CreateReservationEdgeResponseDTO();

        ReserveSlotsCommand reservationCommand = new ReserveSlotsCommand();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO user) {
            reservationCommand.setUserName(user.getUsername());
        }
        reservationCommand.setSlotIds(edgeRequest.getSlotIds());

        createReservationHandler.handle(reservationCommand);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReservationEdgeResponseDTO> delete(@PathVariable("id") Long reservationId) {
        DeleteReservationEdgeResponseDTO response = new DeleteReservationEdgeResponseDTO();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DeleteReservedSlotCommand command = DeleteReservedSlotCommand.builder().reservedSlotId(reservationId).build();

        if (principal instanceof UserDTO user) {
            command.setUsername(user.getUsername());
        }
        deleteReservedSlotHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ReserveSlotEdgeResponseDTO> reserveById(@PathVariable("id") Long slotId) {
        ReserveSlotEdgeResponseDTO response = new ReserveSlotEdgeResponseDTO();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReserveSlotCommand command = new ReserveSlotCommand();

        if (principal instanceof UserDTO user) {
            command.setUserName(user.getUsername());
        }
        command.setSlotId(slotId);

        reserveSlotCommandHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<GetAvailableSlotsEdgeResponseDTO> getAvailableSlots(@Valid @RequestBody GetAvailableSlotsEdgeRequestDTO edgeRequest) {
        GetAvailableSlotsEdgeResponseDTO response = new GetAvailableSlotsEdgeResponseDTO();

        GetAvailableSlotsQuery searchRequest = new GetAvailableSlotsQuery();
        searchRequest.setStartTime(edgeRequest.getStartTime());
        searchRequest.setEndTime(edgeRequest.getEndTime());
        searchRequest.setPageSize(edgeRequest.getPageSize());
        searchRequest.setOffset(edgeRequest.getOffset());

        PaginatedQueryResult<AvailableSlotsDTO> searchResult = getAvailableSlotsQueryHandler.handle(searchRequest);

        List<ReservationEdgeDTO> reservations = searchResult.getData().stream().map(slot -> ReservationEdgeDTO.builder()
                        .id(slot.getId())
                        .startTime(slot.getStartTime())
                        .endTime(slot.getEndTime())
                        .isReserved(slot.getIsReserved())
                        .build())
                .toList();

        response.setReservations(reservations);
        response.setRecordCount(searchResult.getRecordCount());
        response.setPageSize(searchResult.getPageSize());
        response.setOffset(searchResult.getOffset());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reserveNearest")
    public ResponseEntity<ReserveNearestAvailableSlotEdgeResponseDTO> reserveNearestAvailableSlot(@Valid @RequestBody ReserveNearestAvailableSlotEdgeRequestDTO edgeRequest) {
        ReserveNearestAvailableSlotEdgeResponseDTO response = new ReserveNearestAvailableSlotEdgeResponseDTO();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReserveNearestAvailableSlotCommand command = new ReserveNearestAvailableSlotCommand();

        if (principal instanceof UserDTO user) {
            command.setUserName(user.getUsername());
        }
        command.setStartTime(edgeRequest.getStartTime());

        CommandResult<AvailableSlotsDTO> commandResult = reserveNearestSlotHandler.handle(command);
        AvailableSlotsDTO reservedSlot = commandResult.getData();

        response.setId(reservedSlot.getId());
        response.setStartTime(reservedSlot.getStartTime());
        response.setEndTime(reservedSlot.getEndTime());

        return ResponseEntity.ok(response);
    }
}
