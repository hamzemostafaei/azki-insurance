package com.azki.insurance.reservation.service.domain.api.dto.search;

import com.azki.insurance.domain.api.dto.query.BaseSearchCriteria;
import com.azki.insurance.domain.api.dto.query.condition.Condition;
import com.azki.insurance.domain.api.dto.query.condition.ConditionTypeEnum;
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
public class ReservedSlotsCriteriaDTO extends BaseSearchCriteria {

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "reservedSlotsId.userId")
    private Integer userIdEquals;

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "reservedSlotsId.availableSlotId")
    private String slotIdEquals;

}
