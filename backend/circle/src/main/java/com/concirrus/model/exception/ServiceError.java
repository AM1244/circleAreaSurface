package com.concirrus.model.exception;

import lombok.*;

@Getter
@Setter
@Builder
public class ServiceError {

    private String code;

    private String businessMessage;

    private String developerMessage;
}
