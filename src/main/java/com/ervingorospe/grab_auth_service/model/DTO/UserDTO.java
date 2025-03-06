package com.ervingorospe.grab_auth_service.model.DTO;

import com.ervingorospe.grab_auth_service.model.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UserDTO {
    private Long id;
    private String email;
    private User.Role role;
    private LocalDateTime createdAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
