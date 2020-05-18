package com.concirrus.model.exception;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private ServiceError error;

}
