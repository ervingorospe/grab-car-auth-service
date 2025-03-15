package com.ervingorospe.grab_auth_service.model.DTO;

import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class UserDTO {
    private final UUID id;
    private final String email;
    private final User.Role role;
    private final LocalDateTime createdAt;
    private final Boolean active;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String contactNumber;

    public UserDTO(User user, UserDetails userDetails) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getUserRole();
        this.active = user.getActive();
        this.createdAt = user.getCreatedAt();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
        this.birthDate = userDetails.getBirthDate();
        this.contactNumber = userDetails.getContactNumber();
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getUserRole();
        this.active = user.getActive();
        this.createdAt = user.getCreatedAt();
        this.firstName = user.getUserDetails().getFirstName();
        this.lastName = user.getUserDetails().getLastName();
        this.birthDate = user.getUserDetails().getBirthDate();
        this.contactNumber = user.getUserDetails().getContactNumber();
    }
}
