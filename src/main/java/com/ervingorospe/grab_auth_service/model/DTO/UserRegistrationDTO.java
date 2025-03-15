package com.ervingorospe.grab_auth_service.model.DTO;

import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.validate.birthdate.MinAge;
import com.ervingorospe.grab_auth_service.validate.confirmPassword.PasswordMatch;
import com.ervingorospe.grab_auth_service.validate.phoneNumber.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@PasswordMatch(message = "Password doesn't match")
public class UserRegistrationDTO {
    @NotNull(message = "Provide Firstname")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Provide Lastname")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Provide an Email")
    @Email(message = "Provide a valid email")
    private String email;

    @NotNull(message = "Provide Birthdate")
    @MinAge(message = "User must be 10 years old and above")
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotNull(message = "Provide a Contact Number")
    @PhoneNumber(message = "Invalid Phone Number")
    private String contactNumber;

    @NotNull(message = "Provide Password")
    private String password;

    @NotNull(message = "Provide Confirm password")
    @JsonProperty("confirm_password")
    private String confirmPassword;

    private User.Role userRole;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String firstName, String lastName, String email, LocalDate birthDate, String password, String confirmPassword, User.Role userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userRole = userRole;
    }

    public UserRegistrationDTO(String firstName, String confirmPassword, String password, LocalDate birthDate, String email, String lastName) {
        this.firstName = firstName;
        this.confirmPassword = confirmPassword;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.lastName = lastName;
        this.userRole = User.Role.CLIENT;
    }
}
