package com.azki.insurance.common.core.data;

import com.azki.insurance.common.utility.JsonSerializationUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseDTO implements Serializable, Cloneable {

    @Override
    public String toString() {
        return JsonSerializationUtil.objectToPrettyJsonString(this);
    }

    @Override
    public BaseDTO clone() throws CloneNotSupportedException {
        return (BaseDTO) super.clone();
    }
}
