package com.azki.insurance.reservation.service.domain.api.exception;


import com.azki.insurance.domain.core.exception.DomainException;

public class ReservationDomainException extends DomainException {

    public ReservationDomainException(String message) {
        super(message);
    }

    public ReservationDomainException(Throwable cause) {
        super(cause);
    }

    public ReservationDomainException(String pattern, Object... args) {
        super(String.format(pattern, args));
    }

    public ReservationDomainException(String pattern, Throwable cause, Object... args) {
        super(String.format(pattern, args), cause);
    }
}
