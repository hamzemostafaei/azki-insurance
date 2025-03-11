package com.azki.insurance.domain.core.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(String pattern, Object... args) {
        this(String.format(pattern, args));
    }

    public DomainException(String pattern, Throwable cause, Object... args) {
        super(String.format(pattern, args), cause);
    }
}
