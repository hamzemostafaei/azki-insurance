package com.azki.insurance.presentation.domain.api.query;

import com.azki.insurance.presentation.common.core.data.ErrorDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryResult<D> implements Serializable {

    private D data;
    private boolean successful;
    @Singular
    private List<ErrorDTO> errors;

    public static <D> QueryResult<D> success(D data) {
        return QueryResult.<D>builder()
                .data(data)
                .successful(true)
                .errors(Collections.emptyList())
                .build();
    }

    public static <D> QueryResult<D> failure(List<ErrorDTO> errors) {
        return QueryResult.<D>builder()
                .successful(false)
                .errors(errors)
                .build();
    }

    public static <D> QueryResult<D> failure(ErrorDTO error) {
        return failure(Collections.singletonList(error));
    }
}
