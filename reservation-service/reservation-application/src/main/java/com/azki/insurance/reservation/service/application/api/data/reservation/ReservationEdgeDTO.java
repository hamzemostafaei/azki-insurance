package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.common.core.data.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReservationEdgeDTO extends BaseDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("startTime")
    private Date startTime;

    @JsonProperty("endTime")
    private Date endTime;

    @JsonProperty("isReserved")
    private Boolean isReserved;

}
