package com.concirrus.exception.builder;

import com.concirrus.model.exception.ErrorResponse;
import com.concirrus.model.exception.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorBuilder {

    public ServiceError buildServiceError(HttpStatus status, String developerMessage) {
        return ServiceError.builder()
                .code(status.name())
                .businessMessage(status.getReasonPhrase())
                .developerMessage(developerMessage)
                .build();
    }

    public ErrorResponse buildErrorResponse(ServiceError serviceError) {
        return ErrorResponse.builder()
                .error(serviceError)
                .build();
    }

}
