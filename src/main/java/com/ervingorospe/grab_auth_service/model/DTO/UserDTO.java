package com.ervingorospe.grab_auth_service.model.DTO;

import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(
    UUID id,
    String email,
    User.Role role,
    LocalDateTime createdAt,
    Boolean active,
    String firstName,
    String lastName,
    LocalDate birthDate,
    String contactNumber
) {
    // Constructor accepting User and UserDetails separately
    public UserDTO(User user, UserDetails userDetails) {
        this(
            user.getId(),
            user.getEmail(),
            user.getUserRole(),
            user.getCreatedAt(),
            user.getActive(),
            userDetails.getFirstName(),
            userDetails.getLastName(),
            userDetails.getBirthDate(),
            userDetails.getContactNumber()
        );
    }

    // Constructor accepting only User (extracting details from User entity)
    public UserDTO(User user) {
        this(
            user.getId(),
            user.getEmail(),
            user.getUserRole(),
            user.getCreatedAt(),
            user.getActive(),
            user.getUserDetails().getFirstName(),
            user.getUserDetails().getLastName(),
            user.getUserDetails().getBirthDate(),
            user.getUserDetails().getContactNumber()
        );
    }
}
