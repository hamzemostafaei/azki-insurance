package com.azki.insurance.domain.api.dto.query.condition;


public class InCondition<T> extends BaseConditionItem<T> {

    public InCondition(T conditionData) {
        super(ConditionTypeEnum.IN, conditionData);
    }
}
