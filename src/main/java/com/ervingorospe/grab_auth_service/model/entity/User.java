package com.ervingorospe.grab_auth_service.model.entity;

import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "user_role")
    private Role userRole;

    @Column
    private Boolean active;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserDetails userDetails;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public User() {
        this.userRole = Role.CLIENT;
        this.active = true; // to be change later when done setting up registration token
    }

    public User(UserRegistrationDTO userRegistrationDTO) {
        this.email = userRegistrationDTO.getEmail();
        this.password = userRegistrationDTO.getPassword();
        this.userRole = Optional.ofNullable(userRegistrationDTO.getUserRole()).orElse(Role.CLIENT);
        this.active = true;
    }

    public enum Role {
        CLIENT, ADMIN, DRIVER
    }
}
