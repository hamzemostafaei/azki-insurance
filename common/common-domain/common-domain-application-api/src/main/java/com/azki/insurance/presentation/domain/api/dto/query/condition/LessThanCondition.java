package com.azki.insurance.presentation.domain.api.dto.query.condition;


public class LessThanCondition<T> extends BaseConditionItem<T> {

    public LessThanCondition(T conditionData) {
        super(ConditionTypeEnum.LESS_THAN, conditionData);
    }
}
