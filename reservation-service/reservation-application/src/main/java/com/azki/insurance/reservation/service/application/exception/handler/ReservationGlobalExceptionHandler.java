package com.azki.insurance.reservation.service.application.exception.handler;

import com.azki.insurance.application.handler.GlobalExceptionHandler;
import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.reservation.service.domain.api.exception.ReservationDomainException;
import com.azki.insurance.reservation.service.domain.api.exception.ReservationNotFoundException;
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
    @ExceptionHandler(value = {ReservationDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ReservationDomainException customerDomainException) {
        if (log.isErrorEnabled()) {
            log.error(customerDomainException.getMessage(), customerDomainException);
        }
        return new ErrorDTO(ErrorCodeEnum.INCONSISTENT_DATA, customerDomainException.getMessage(), "BadRequest");
    }

    @ResponseBody
    @ExceptionHandler(value = {ReservationNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(ReservationNotFoundException customerNotFoundException) {
        if (log.isErrorEnabled()) {
            log.error(customerNotFoundException.getMessage(), customerNotFoundException);
        }
        return new ErrorDTO(ErrorCodeEnum.DATA_NOT_FOUND, customerNotFoundException.getMessage(), "Reservation");
    }
}
