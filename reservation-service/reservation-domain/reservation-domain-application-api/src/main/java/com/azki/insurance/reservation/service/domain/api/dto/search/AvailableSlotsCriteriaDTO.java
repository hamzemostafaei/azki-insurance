package com.azki.insurance.reservation.service.domain.api.dto.search;

import com.azki.insurance.domain.api.dto.query.BaseSearchCriteria;
import com.azki.insurance.domain.api.dto.query.condition.Condition;
import com.azki.insurance.domain.api.dto.query.condition.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AvailableSlotsCriteriaDTO extends BaseSearchCriteria {

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "id")
    private Integer reservationIdEquals;

    @Condition(type = ConditionTypeEnum.GREATER_THAN_OR_EQUAL_TO, fieldName = "startTime")
    private Date startTimeGreaterThanOrEqual;

    @Condition(type = ConditionTypeEnum.LESS_THAN_OR_EQUAL_TO, fieldName = "startTime")
    private Date startTimeLessThanOrEqual;

    @Condition(type = ConditionTypeEnum.GREATER_THAN_OR_EQUAL_TO, fieldName = "endTime")
    private Date endTimeGreaterThanOrEqual;

    @Condition(type = ConditionTypeEnum.LESS_THAN_OR_EQUAL_TO, fieldName = "endTime")
    private Date endTimeLessThanOrEqual;

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "isReserved")
    private Boolean isReserved;

}
