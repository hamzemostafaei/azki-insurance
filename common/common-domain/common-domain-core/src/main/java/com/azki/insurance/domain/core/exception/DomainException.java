package com.azki.insurance.domain.core.exception;

import com.azki.insurance.common.core.data.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DomainException extends RuntimeException {
    protected List<ErrorDTO> errors;
}