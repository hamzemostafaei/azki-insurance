package com.azki.insurance.reservation.service.application.controller.api;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.input.CommandHandler;
import com.azki.insurance.reservation.service.application.api.data.reservation.*;
import com.azki.insurance.reservation.service.domain.api.command.CreateReservationCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CommandHandler<CreateReservationCommand, CommandResult<AvailableSlotsDTO>> createReservationHandler;

    @PostMapping
    public ResponseEntity<CreateReservationEdgeResponseDTO> reserve(@RequestBody CreateReservationEdgeRequestDTO edgeRequest) {
        CreateReservationEdgeResponseDTO response = new CreateReservationEdgeResponseDTO();

        CreateReservationCommand reservationCommand = CreateReservationCommand.builder()
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
        return ResponseEntity.ok(response);
    }
}
