package com.azki.insurance.domain.api.dto.query.condition;


import com.azki.insurance.common.utility.ReflectionUtil;
import lombok.Getter;

@Getter
public abstract class BaseConditionItem<T> implements GenericConditionItem<T> {

    private final Class<T> conditionDataType;
    private final ConditionTypeEnum conditionType;
    private final T conditionData;

    public BaseConditionItem(ConditionTypeEnum conditionType) {
        this(conditionType, null);
    }

    @SuppressWarnings("unchecked")
    public BaseConditionItem(ConditionTypeEnum conditionType, T conditionData) {

        assert conditionType != null;

        if (conditionData != null) {
            this.conditionDataType = (Class<T>) conditionData.getClass();
        } else {
            this.conditionDataType = ReflectionUtil.getGenericParameterType(this, 0);
        }

        this.conditionType = conditionType;
        this.conditionData = conditionData;

    }

    @Override
    public T getConditionData() {
        return conditionData;
    }

}
