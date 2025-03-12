package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.api.data.BaseEdgeResponseDTO;
import com.azki.insurance.api.data.BasePaginatedEdgeResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetReservationsEdgeResponseDTO extends BasePaginatedEdgeResponseDTO {

    @JsonProperty("reservations")
    private List<ReservationEdgeDTO> reservations;
}
