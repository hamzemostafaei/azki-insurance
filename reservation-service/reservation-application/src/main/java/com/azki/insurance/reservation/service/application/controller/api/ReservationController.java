package com.azki.insurance.reservation.service.application.controller.api;

import com.azki.insurance.reservation.service.application.api.data.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @PostMapping
    public ResponseEntity<CreateReservationEdgeResponseDTO> reserve(@RequestBody CreateReservationEdgeRequestDTO edgeRequest) {
        CreateReservationEdgeResponseDTO response = new CreateReservationEdgeResponseDTO();

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
