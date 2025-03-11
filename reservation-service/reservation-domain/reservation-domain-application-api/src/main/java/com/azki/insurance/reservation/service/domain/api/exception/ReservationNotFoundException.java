package com.azki.insurance.reservation.service.domain.api.exception;


import com.azki.insurance.domain.core.exception.DomainException;

public class ReservationNotFoundException extends DomainException {

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public ReservationNotFoundException(String pattern, Object... args) {
        super(String.format(pattern, args));
    }

    public ReservationNotFoundException(String pattern, Throwable cause, Object... args) {
        super(String.format(pattern, args), cause);
    }
}
