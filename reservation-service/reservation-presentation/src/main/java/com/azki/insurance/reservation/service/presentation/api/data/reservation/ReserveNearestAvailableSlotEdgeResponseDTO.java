package com.azki.insurance.reservation.service.presentation.api.data.reservation;

import com.azki.insurance.presentation.api.data.BaseEdgeResponseDTO;
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
