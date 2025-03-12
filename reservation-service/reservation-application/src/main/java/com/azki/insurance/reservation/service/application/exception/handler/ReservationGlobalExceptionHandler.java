package com.azki.insurance.reservation.service.application.exception.handler;

import com.azki.insurance.api.data.BaseEdgeResponseDTO;
import com.azki.insurance.application.handler.GlobalExceptionHandler;
import com.azki.insurance.domain.core.exception.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ReservationGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {DomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseEdgeResponseDTO handleDomainException(DomainException ex, HttpServletRequest request) {

        if (log.isErrorEnabled()) {
            log.error("DomainException occurred - Path: [{}], Errors: {}",
                    request.getServletPath(),
                    ex.getErrors()
            );
        }
        BaseEdgeResponseDTO response = new BaseEdgeResponseDTO();
        response.setErrors(ex.getErrors());

        return response;
    }
}
