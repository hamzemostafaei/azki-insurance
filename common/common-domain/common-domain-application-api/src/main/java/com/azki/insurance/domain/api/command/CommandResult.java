package com.azki.insurance.domain.api.command;

import com.azki.insurance.common.core.data.ErrorDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class CommandResult<D> {

    private D data;
    private boolean successful;
    @Singular
    private List<ErrorDTO> errors;

    public static <D> CommandResult<D> success(D data) {
        return CommandResult.<D>builder()
                .data(data)
                .successful(true)
                .errors(Collections.emptyList())
                .build();
    }

    public static <D> CommandResult<D> success() {
        return CommandResult.<D>builder()
                .successful(true)
                .build();
    }

    public static <D> CommandResult<D> failure(List<ErrorDTO> errors) {
        return CommandResult.<D>builder()
                .successful(false)
                .errors(errors)
                .build();
    }

    public static <D> CommandResult<D> failure(ErrorDTO error) {
        return failure(Collections.singletonList(error));
    }
}
