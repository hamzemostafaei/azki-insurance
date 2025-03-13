package com.azki.insurance.reservation.service.presentation.api.data.reservation;

import com.azki.insurance.presentation.api.data.BasePaginatedEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetAvailableSlotsEdgeRequestDTO extends BasePaginatedEdgeRequestDTO {

    @JsonProperty("startTime")
    private Date startTime;

    @JsonProperty("endTime")
    private Date endTime;

}
