package com.azki.insurance.domain.application.api.dto.query.condition;

public class NotLikeCondition<T> extends BaseConditionItem<T> {

    public NotLikeCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_LIKE, conditionData);
    }

}
