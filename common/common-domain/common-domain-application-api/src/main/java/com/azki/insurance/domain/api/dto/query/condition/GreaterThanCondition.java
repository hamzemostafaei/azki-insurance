package com.azki.insurance.domain.api.dto.query.condition;


public class GreaterThanCondition<T> extends BaseConditionItem<T> {

    public GreaterThanCondition(T conditionData) {
        super(ConditionTypeEnum.GREATER_THAN, conditionData);
    }
}
