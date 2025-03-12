package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateReservationEdgeRequestDTO extends BaseEdgeRequestDTO {

    @JsonProperty("startTime")
    private Date startTime;

    @JsonProperty("endTime")
    private Date endTime;
}
