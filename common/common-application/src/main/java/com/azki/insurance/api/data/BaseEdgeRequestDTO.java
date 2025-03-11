package com.azki.insurance.api.data;

import com.azki.insurance.common.core.data.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEdgeRequestDTO extends BaseDTO {

}
