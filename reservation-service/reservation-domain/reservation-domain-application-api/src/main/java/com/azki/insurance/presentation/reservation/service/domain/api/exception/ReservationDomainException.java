package com.azki.insurance.presentation.reservation.service.domain.api.exception;

import com.azki.insurance.presentation.common.core.data.ErrorDTO;
import com.azki.insurance.presentation.domain.core.exception.DomainException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReservationDomainException extends DomainException {

    public ReservationDomainException() {
        super();
    }

    public ReservationDomainException(List<ErrorDTO> errors) {
        super(errors);
    }
}
