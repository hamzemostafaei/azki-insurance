package com.azki.insurance.presentation.common.core.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodeEnum {

    GENERAL_ERROR(0),
    MANDATORY_FIELD(1),
    DATA_FORMAT_MISMATCH(2),
    DUPLICATE_DATA(3),
    INCONSISTENT_DATA(4),
    SERVICE_CONTRACTS_VIOLATION(5),
    DATA_NOT_FOUND(6),
    not_authorized_for_data(7),
    SERVICE_TEMPORARY_DOWN(8),
    INTERNAL_SERVICE_ERROR(9),
    EXTERNAL_SERVICE_TEMPORARY_DOWN(10),
    NO_RESPONSE_FROM_EXTERNAL_SERVICE(11),
    PLEASE_REPEAT_THE_REQUEST(12),
    UNABLE_TO_PROVIDE_THE_REQUESTED_SERVICE(13),
    SECURITY_ERROR(14),
    OUT_OF_BOUNDS_DATA(15),
    INACTIVE_REFERENCE(16),
    EXPIRED_REFERENCE(17),
    MANUAL_REJECTION(18);

    private static final Map<Integer, ErrorCodeEnum> VALUE_MAP = new HashMap<>();

    static {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            VALUE_MAP.put(value.getErrorCode(), value);
        }
    }

    private final int errorCode;

    ErrorCodeEnum(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonCreator
    public static ErrorCodeEnum getByValue(Integer errorCode) {
        ErrorCodeEnum value = VALUE_MAP.get(errorCode);
        if (value == null) {
            throw new IllegalArgumentException("Bad error code [" + errorCode + "] is provided.");
        }

        return value;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
