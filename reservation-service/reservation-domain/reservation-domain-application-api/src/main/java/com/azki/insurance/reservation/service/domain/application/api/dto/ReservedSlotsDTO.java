package com.azki.insurance.reservation.service.domain.application.api.dto;

import com.azki.insurance.domain.application.api.dto.BaseVersionedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReservedSlotsDTO extends BaseVersionedDTO {

    private Long id;
    private Long availableSlotId;
    private Long userId;

}
