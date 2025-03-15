package com.ervingorospe.grab_auth_service.validate.confirmPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ValidatePassword implements ConstraintValidator<PasswordMatch, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            Field passwordField = obj.getClass().getDeclaredField("password");
            Field confirmPasswordField = obj.getClass().getDeclaredField("confirmPassword");

            passwordField.setAccessible(true);
            confirmPasswordField.setAccessible(true);

            String password = (String) passwordField.get(obj);
            String confirmPassword = (String) confirmPasswordField.get(obj);

            return password != null && password.equals(confirmPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
