package com.ervingorospe.grab_auth_service.model.DTO;

import com.ervingorospe.grab_auth_service.validate.confirmPassword.PasswordMatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@PasswordMatch(message = "Password doesn't match")
public record PasswordDTO (
        @NotNull(message = "Provide Current Password")
        @JsonProperty("current_password")
        String currentPassword,

        @NotNull(message = "Provide New Password")
        @JsonProperty("new_password")
        String password,

        @NotNull(message = "Provide Confirm Password")
        @JsonProperty("confirm_password")
        String confirmPassword
) {
}
