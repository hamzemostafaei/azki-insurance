package com.azki.insurance.presentation.domain.api.dto.query.condition;


public class EqualCondition<T> extends BaseConditionItem<T> {

    public EqualCondition(T conditionData) {
        super(ConditionTypeEnum.EQUAL, conditionData);
    }
}