package com.azki.insurance.reservation.service.domain.api.exception;

import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.domain.core.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
