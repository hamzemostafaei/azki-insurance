package com.azki.insurance.domain.api.dto.query.condition;


public class GreaterThanOrEqualToCondition<T> extends BaseConditionItem<T> {

    public GreaterThanOrEqualToCondition(T conditionData) {
        super(ConditionTypeEnum.GREATER_THAN_OR_EQUAL_TO, conditionData);
    }
}
