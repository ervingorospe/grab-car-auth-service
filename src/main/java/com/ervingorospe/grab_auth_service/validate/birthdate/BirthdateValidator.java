package com.ervingorospe.grab_auth_service.validate.birthdate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class BirthdateValidator implements ConstraintValidator<MinAge, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        return birthDate != null && Period.between(birthDate, LocalDate.now()).getYears() >= 10;
    }
}
