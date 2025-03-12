package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateReservationEdgeRequestDTO extends BaseEdgeRequestDTO {

    @JsonProperty("slotIds")
    @NotEmpty(message = "At least one slot ID is required.")
    @Size(min = 1, max = 10, message = "The number of slot IDs must be between 1 and 10.")
    private List<Integer> slotIds;
}
