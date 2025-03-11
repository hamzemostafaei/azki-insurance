package com.azki.insurance.domain.api.dto.query.condition;


public class NotInCondition<T> extends BaseConditionItem<T> {

    public NotInCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_IN, conditionData);
    }
}
