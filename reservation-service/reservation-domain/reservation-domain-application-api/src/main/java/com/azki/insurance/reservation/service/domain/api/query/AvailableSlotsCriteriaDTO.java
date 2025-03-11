package com.azki.insurance.reservation.service.domain.api.query;

import com.azki.insurance.domain.api.dto.BaseSearchCriteria;
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
public class AvailableSlotsCriteriaDTO extends BaseSearchCriteria {
    private Integer reservationId;
}
