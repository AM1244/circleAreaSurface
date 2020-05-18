package com.concirrus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class Circle {


    @Valid
    @NotNull(message = "Cannot be null")
    @Min(value = 0, message = "The value must be positive")
    private Double radius;

    private Double area;
}
