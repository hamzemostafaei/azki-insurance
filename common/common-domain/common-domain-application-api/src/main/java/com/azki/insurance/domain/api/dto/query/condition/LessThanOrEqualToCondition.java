package com.azki.insurance.domain.api.dto.query.condition;


public class LessThanOrEqualToCondition<T> extends BaseConditionItem<T> {

    public LessThanOrEqualToCondition(T conditionData) {
        super(ConditionTypeEnum.LESS_THAN_OR_EQUAL_TO, conditionData);
    }
}
