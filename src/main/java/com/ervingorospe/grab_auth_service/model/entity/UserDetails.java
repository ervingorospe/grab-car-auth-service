package com.ervingorospe.grab_auth_service.model.entity;

import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UserDetails() {
    }

    public UserDetails(String firstName, String lastName, LocalDate birthDate, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.contactNumber = contactNumber;
    }

   public UserDetails(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.firstName();
        this.lastName = userRegistrationDTO.lastName();
        this.birthDate = userRegistrationDTO.birthDate();
        this.contactNumber = userRegistrationDTO.contactNumber();
   }
}
