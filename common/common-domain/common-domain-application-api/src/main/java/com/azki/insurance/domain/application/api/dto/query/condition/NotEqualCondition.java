package com.azki.insurance.domain.application.api.dto.query.condition;


public class NotEqualCondition<T> extends BaseConditionItem<T> {

    public NotEqualCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_EQUAL, conditionData);
    }
}
