package com.azki.insurance.domain.application.api.dto.query.condition;


public class LikeCondition<T> extends BaseConditionItem<T> {

    public LikeCondition(T conditionData) {
        super(ConditionTypeEnum.LIKE, conditionData);
    }

}