package com.azki.insurance.presentation.domain.api.dto.query.condition;


public class NotRegexpLikeCondition<T> extends BaseConditionItem<T> {

    public NotRegexpLikeCondition(T conditionData) {
        super(ConditionTypeEnum.NOT_REGEXP_LIKE, conditionData);
    }

}
