package com.azki.insurance.domain.api.dto.query.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BetweenCriteriaDTO<F, T> {

    private F from;
    private T to;

    public BetweenCriteriaDTO() {
    }

    public BetweenCriteriaDTO(F from, T to) {
        this.from = from;
        this.to = to;
    }
}
