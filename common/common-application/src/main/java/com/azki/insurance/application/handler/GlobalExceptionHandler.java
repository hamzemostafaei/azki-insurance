package com.azki.insurance.application.handler;

import com.azki.insurance.api.data.BaseEdgeResponseDTO;
import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseEdgeResponseDTO handleException(Exception exception) {
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception);
        }
        BaseEdgeResponseDTO response = new BaseEdgeResponseDTO();
        response.addError(new ErrorDTO(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, "InternalServerError"));

        return response;
    }

    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseEdgeResponseDTO handleException(ValidationException validationException) {
        ErrorDTO errorDTO;
        if (validationException instanceof ConstraintViolationException constraintViolationException) {
            String violations = extractViolationsFromException(constraintViolationException);
            if (log.isErrorEnabled()) {
                log.error(violations, validationException);
            }
            errorDTO = new ErrorDTO(ErrorCodeEnum.INCONSISTENT_DATA, violations, "ConstraintViolationException");
        } else {
            String exceptionMessage = validationException.getMessage();
            if (log.isErrorEnabled()) {
                log.error(exceptionMessage, validationException);
            }
            errorDTO = new ErrorDTO(ErrorCodeEnum.INCONSISTENT_DATA, exceptionMessage, "ConstraintViolationException");
        }

        BaseEdgeResponseDTO response = new BaseEdgeResponseDTO();
        response.addError(errorDTO);

        return response;
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

}