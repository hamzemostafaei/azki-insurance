package com.azki.insurance.domain.api.dto.query.condition;


public class NotNullCondition<T> extends BaseConditionItem<T> {

    public NotNullCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_NULL, conditionData);
    }

}
