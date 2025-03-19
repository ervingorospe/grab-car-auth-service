package com.ervingorospe.grab_auth_service.model.entity;

import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserAddress> address;

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
        this.active = false; // to be change later when done setting up registration token
    }

    public User(UserRegistrationDTO userRegistrationDTO) {
        this.email = userRegistrationDTO.email();
        this.password = userRegistrationDTO.password();
        this.userRole = Optional.ofNullable(userRegistrationDTO.userRole()).orElse(Role.CLIENT);
        this.active = false;
    }

    public enum Role {
        CLIENT, ADMIN, DRIVER
    }
}
