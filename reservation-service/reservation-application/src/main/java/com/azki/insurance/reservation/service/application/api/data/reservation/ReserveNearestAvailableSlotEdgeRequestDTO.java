package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReserveNearestAvailableSlotEdgeRequestDTO extends BaseEdgeRequestDTO {

    @FutureOrPresent(message = "Start time must be in the present or future.")
    @JsonProperty("startTime")
    private Date startTime;

}
