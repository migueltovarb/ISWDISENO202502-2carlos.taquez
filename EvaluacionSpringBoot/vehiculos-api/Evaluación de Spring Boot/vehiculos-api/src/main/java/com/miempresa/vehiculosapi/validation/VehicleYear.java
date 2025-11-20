package com.miempresa.vehiculosapi.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = VehicleYearValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface VehicleYear {

    String message() default "El año debe estar entre 1950 y el año actual + 1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

