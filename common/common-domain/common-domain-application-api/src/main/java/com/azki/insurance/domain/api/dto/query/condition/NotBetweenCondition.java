package com.azki.insurance.domain.api.dto.query.condition;


public class NotBetweenCondition<T> extends BaseConditionItem<T> {

    public NotBetweenCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_BETWEEN, conditionData);
    }
}
