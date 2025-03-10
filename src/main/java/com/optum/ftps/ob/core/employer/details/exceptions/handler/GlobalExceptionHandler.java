package com.optum.ftps.ob.core.employer.details.exceptions.handler;

import com.optum.ftps.ob.core.employer.details.exceptions.EmployerDetailsException;
import com.optum.ftps.ob.core.employer.details.exceptions.NotFoundException;
import com.optum.ftps.ob.core.employer.details.exceptions.ServiceException;
import com.optum.ftps.ob.core.employer.details.exceptions.ValidationException;
import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorCodes;
import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundExceptions(
            EmployerDetailsException ex, WebRequest request) {
        return handle(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handleValidationException(
            EmployerDetailsException ex, WebRequest request) {
        return handle(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> handleServiceException(
            EmployerDetailsException ex, WebRequest request) {
        return handle(ex, HttpStatus.BAD_GATEWAY, request);
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleUnexpectedError(Exception ex, WebRequest request) {
        log.error("The server failed with unexpected exception", ex);
        var errorItem =
                ErrorItem.builder()
                        .severity("1")
                        .statusCode(ErrorCodes.INTERNAL_SERVER_ERROR.toString())
                        .statusDescription(ErrorCodes.INTERNAL_SERVER_ERROR.description)
                        .build();
        var errors = List.of(errorItem);
        var exception = new ServiceException(errors);
        return handle(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handle(
            EmployerDetailsException ex, HttpStatus status, WebRequest request) {
        return handle(ex, ex.getErrors(), status, request);
    }

    private ResponseEntity<Object> handle(
            Exception ex, List<ErrorItem> errors, HttpStatus status, WebRequest request) {
        var body = buildBody(errors);
        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    private ErrorResponse buildBody(List<ErrorItem> errors) {
        var response = ErrorResponse.builder().build();
        response.getErrors().addAll(errors);
        return response;
    }
}
