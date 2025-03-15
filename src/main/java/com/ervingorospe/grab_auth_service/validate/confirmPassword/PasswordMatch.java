package com.ervingorospe.grab_auth_service.validate.confirmPassword;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatePassword.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String message() default "Password Doesn't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
