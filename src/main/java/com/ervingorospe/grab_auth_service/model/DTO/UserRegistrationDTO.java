package com.ervingorospe.grab_auth_service.model.DTO;

import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.validate.birthdate.MinAge;
import com.ervingorospe.grab_auth_service.validate.confirmPassword.PasswordMatch;
import com.ervingorospe.grab_auth_service.validate.phoneNumber.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@PasswordMatch(message = "Password doesn't match")
public record UserRegistrationDTO(
    @NotNull(message = "Provide Firstname")
    @JsonProperty("first_name")
    String firstName,

    @NotNull(message = "Provide Lastname")
    @JsonProperty("last_name")
    String lastName,

    @NotNull(message = "Provide an Email")
    @Email(message = "Provide a valid email")
    String email,

    @NotNull(message = "Provide Birthdate")
    @MinAge(message = "User must be 10 years old and above")
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate birthDate,

    @NotNull(message = "Provide a Contact Number")
    @PhoneNumber(message = "Invalid Phone Number")
    @JsonProperty("contact_number")
    String contactNumber,

    @NotNull(message = "Provide Password")
    String password,

    @NotNull(message = "Provide Confirm password")
    @JsonProperty("confirm_password")
    String confirmPassword,

    @JsonProperty("role")
    User.Role userRole
) {
    // Default constructor setting userRole to CLIENT
    public UserRegistrationDTO(
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        String contactNumber,
        String password,
        String confirmPassword,
        String userRole
    ) {
        this(
            firstName,
            lastName,
            email,
            birthDate,
            contactNumber,
            password,
            confirmPassword,
            (userRole != null && !userRole.isBlank())
                    ? User.Role.valueOf(userRole.toUpperCase()) // Convert String to Enum
                    : User.Role.CLIENT // Default to CLIENT

        );
    }
}