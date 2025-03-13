package com.azki.insurance.presentation.domain.api.dto.query.condition;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ConditionTypeEnum {

    EQUAL(0, EqualCondition.class),
    NOT_EQUAL(1, NotEqualCondition.class),
    GREATER_THAN(2, GreaterThanCondition.class),
    GREATER_THAN_OR_EQUAL_TO(3, GreaterThanOrEqualToCondition.class),
    LESS_THAN(4, LessThanCondition.class),
    LESS_THAN_OR_EQUAL_TO(5, LessThanOrEqualToCondition.class),
    IN(6, InCondition.class),
    NOT_IN(7, NotInCondition.class),
    BETWEEN(8, BetweenCondition.class),
    NOT_BETWEEN(9, NotBetweenCondition.class),
    LIKE(10, LikeCondition.class),
    NOT_LIKE(11, NotLikeCondition.class),
    REGEXP_LIKE(12, RegexpLikeCondition.class),
    NOT_REGEXP_LIKE(13, NotRegexpLikeCondition.class),
    NULL(14, NullCondition.class),
    NOT_NULL(15, NotNullCondition.class);

    private static final Map<Integer, ConditionTypeEnum> VALUE_MAP = new HashMap<>();

    static {
        for (ConditionTypeEnum value : ConditionTypeEnum.values()) {
            VALUE_MAP.put(value.getTypeCode(), value);
        }
    }

    private final int typeCode;

    private final Class<?> conditionItemType;

    ConditionTypeEnum(int typeCode, Class<?> conditionItemType) {
        this.typeCode = typeCode;
        this.conditionItemType = conditionItemType;
    }

    @SuppressWarnings("unused")
    public static ConditionTypeEnum getByValue(Integer typeCode) {
        if (typeCode == null) {
            return null;
        }

        ConditionTypeEnum value = VALUE_MAP.get(typeCode);
        if (value == null) {
            throw new IllegalArgumentException("Bad type code [" + typeCode + "] is provided.");
        }

        return value;
    }

}