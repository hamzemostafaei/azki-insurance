package com.azki.insurance.presentation.domain.api.dto.query.condition;


public class NullCondition<T> extends BaseConditionItem<T> {

    public NullCondition(T conditionData) {
        super(ConditionTypeEnum.NULL, conditionData);
    }

}
