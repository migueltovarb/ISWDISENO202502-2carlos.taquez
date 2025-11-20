package com.miempresa.vehiculosapi.validation;

import java.time.Year;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VehicleYearValidator implements ConstraintValidator<VehicleYear, Integer> {

    private static final int MIN_YEAR = 1950;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        int maxYear = Year.now().plusYears(1).getValue();
        return value >= MIN_YEAR && value <= maxYear;
    }
}

