package com.azki.insurance.presentation.domain.api.dto.query.condition;


public class NotNullCondition<T> extends BaseConditionItem<T> {

    public NotNullCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_NULL, conditionData);
    }

}
