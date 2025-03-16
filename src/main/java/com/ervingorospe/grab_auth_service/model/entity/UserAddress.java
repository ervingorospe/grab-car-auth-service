package com.ervingorospe.grab_auth_service.model.entity;

import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_addresses")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "postal_code",nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserAddress() {
    }

    public UserAddress(String label, String street, String city, String state, String postalCode, String country, double latitude, double longitude, User user) {
        this.label = label;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

    public UserAddress(UserRegistrationDTO userRegistrationDTO) {
        this.label = userRegistrationDTO.address().label();
        this.street =  userRegistrationDTO.address().street();
        this.city =  userRegistrationDTO.address().city();
        this.state =  userRegistrationDTO.address().state();
        this.postalCode =  userRegistrationDTO.address().postalCode();
        this.country =  userRegistrationDTO.address().country();
        this.latitude = userRegistrationDTO.address().latitude();
        this.longitude = userRegistrationDTO.address().longitude();
    }
}
