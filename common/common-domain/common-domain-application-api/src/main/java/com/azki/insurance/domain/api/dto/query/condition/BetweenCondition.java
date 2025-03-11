package com.azki.insurance.domain.api.dto.query.condition;



public class BetweenCondition<T extends BetweenCriteriaDTO<?, ?>> extends BaseConditionItem<T> {

    public BetweenCondition(T conditionData) {
        super(ConditionTypeEnum.BETWEEN, conditionData);
    }
}
