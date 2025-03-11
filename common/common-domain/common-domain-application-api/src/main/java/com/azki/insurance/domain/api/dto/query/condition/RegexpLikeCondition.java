package com.azki.insurance.domain.api.dto.query.condition;


public class RegexpLikeCondition<T> extends BaseConditionItem<T> {

    public RegexpLikeCondition(T conditionData) {
        super(ConditionTypeEnum.REGEXP_LIKE, conditionData);
    }

}
