package com.azki.insurance.domain.api.command;

import com.azki.insurance.common.core.data.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CommandResult<D> {
    private D data;
    private boolean successful;
    private List<ErrorDTO> errors;
}
