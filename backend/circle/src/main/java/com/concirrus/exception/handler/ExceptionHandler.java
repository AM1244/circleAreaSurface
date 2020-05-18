package com.concirrus.exception.handler;

import com.concirrus.exception.builder.ErrorBuilder;
import com.concirrus.model.exception.ErrorResponse;
import com.concirrus.model.exception.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    private ErrorBuilder errorBuilder;

    @Autowired
    public ExceptionHandler(final ErrorBuilder errorBuilder) {
        this.errorBuilder = errorBuilder;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error(ex.getMessage(), ex);

        ServiceError serviceError = errorBuilder.buildServiceError(status, ex.getMessage());

        ErrorResponse errorResponse = errorBuilder.buildErrorResponse(serviceError);

        return ResponseEntity.status(status).body(errorResponse);
    }

}
