package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateReservationEdgeRequestDTO extends BaseEdgeRequestDTO {

    @JsonProperty("slotIds")
    private List<Integer> slotIds;
}
