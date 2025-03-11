package com.azki.insurance.reservation.service.application.exception.handler;

import com.azki.insurance.application.handler.GlobalExceptionHandler;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.domain.core.exception.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ReservationGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {DomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDTO> handleDomainException(DomainException ex, HttpServletRequest request) {
        if (log.isErrorEnabled()) {
            log.error("DomainException occurred - Path: [{}], Errors: {}",
                    request.getServletPath(),
                    ex.getErrors()
            );
        }
        return ex.getErrors();
    }
}
