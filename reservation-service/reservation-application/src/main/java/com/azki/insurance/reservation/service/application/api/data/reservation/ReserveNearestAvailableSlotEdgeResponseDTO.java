package com.azki.insurance.reservation.service.application.api.data.reservation;

import com.azki.insurance.api.data.BaseEdgeResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReserveNearestAvailableSlotEdgeResponseDTO extends BaseEdgeResponseDTO {
    private Long id;
    private Date startTime;
    private Date endTime;
}
