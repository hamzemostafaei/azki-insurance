package com.azki.insurance.presentation.reservation.service.domain.api.dto;

import com.azki.insurance.presentation.domain.api.dto.BaseVersionedDTO;
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
