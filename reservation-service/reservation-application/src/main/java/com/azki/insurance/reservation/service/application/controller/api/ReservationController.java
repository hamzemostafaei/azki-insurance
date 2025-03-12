package com.azki.insurance.reservation.service.application.controller.api;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.input.CommandHandler;
import com.azki.insurance.domain.api.input.QueryHandler;
import com.azki.insurance.domain.api.query.PaginatedQueryResult;
import com.azki.insurance.reservation.service.application.api.data.reservation.*;
import com.azki.insurance.reservation.service.domain.api.command.CreateReservationCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.query.SearchReservations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CommandHandler<CreateReservationCommand, CommandResult<AvailableSlotsDTO>> createReservationHandler;
    private final QueryHandler<SearchReservations, PaginatedQueryResult<AvailableSlotsDTO>> searchReservationHandler;

    @PostMapping
    public ResponseEntity<CreateReservationEdgeResponseDTO> reserve(@RequestBody CreateReservationEdgeRequestDTO edgeRequest) {
        CreateReservationEdgeResponseDTO response = new CreateReservationEdgeResponseDTO();

        CreateReservationCommand reservationCommand =
                CreateReservationCommand.builder()
                        .startTime(edgeRequest.getStartTime())
                        .endTime(edgeRequest.getEndTime())
                        .build();

        CommandResult<AvailableSlotsDTO> result = createReservationHandler.handle(reservationCommand);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReservationEdgeResponseDTO> delete(@PathVariable("id") Integer reservationId) {
        DeleteReservationEdgeResponseDTO response = new DeleteReservationEdgeResponseDTO();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetReservationsEdgeResponseDTO> getReservations(@RequestBody GetReservationsEdgeRequestDTO edgeRequest) {
        GetReservationsEdgeResponseDTO response = new GetReservationsEdgeResponseDTO();

        SearchReservations searchRequest = new SearchReservations();
        searchRequest.setStartTime(edgeRequest.getStartTime());
        searchRequest.setEndTime(edgeRequest.getEndTime());
        searchRequest.setPageSize(edgeRequest.getPageSize());
        searchRequest.setOffset(edgeRequest.getOffset());

        PaginatedQueryResult<AvailableSlotsDTO> searchResult = searchReservationHandler.handle(searchRequest);

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
}
