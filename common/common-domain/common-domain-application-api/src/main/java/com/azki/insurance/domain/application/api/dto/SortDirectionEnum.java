package com.azki.insurance.domain.application.api.dto;

import java.util.HashMap;
import java.util.Map;

public enum SortDirectionEnum {

    ASC(0),
    DESC(1);

    private static final Map<Integer, SortDirectionEnum> VALUE_MAP = new HashMap<>();

    static {
        for (SortDirectionEnum value : SortDirectionEnum.values()) {
            VALUE_MAP.put(value.getCode(), value);
        }
    }

    private final int code;

    SortDirectionEnum(int code) {
        this.code = code;
    }

    public static SortDirectionEnum getByValue(Integer code) {
        if (code == null) {
            return null;
        }

        SortDirectionEnum value = VALUE_MAP.get(code);
        if (value == null) {
            throw new IllegalArgumentException("Bad code [" + code + "] is provided.");
        }

        return value;
    }

    public int getCode() {
        return code;
    }

}
