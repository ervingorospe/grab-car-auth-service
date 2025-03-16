package com.ervingorospe.grab_auth_service.model.DTO;


import com.ervingorospe.grab_auth_service.model.entity.UserAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UserAddressDTO(
    @NotNull(message = "Address label can't be empty")
    String label,

    @NotNull(message = "Street can't be empty")
    String street,

    @NotNull(message = "City can't be empty")
    String city,

    @NotNull(message = "State can't be empty")
    String state,

    @NotNull(message = "Postal code can't be empty")
    @JsonProperty("postal_code")
    String postalCode,

    @NotNull(message = "Country can't be empty")
    String country,

    @NotNull(message = "Latitude can't be empty")
    double latitude,

    @NotNull(message = "Longitude can't be empty")
    double longitude
) {
    public UserAddressDTO(UserAddress address) {
        this(
            address.getLabel(),
            address.getStreet(),
            address.getCity(),
            address.getState(),
            address.getPostalCode(),
            address.getCountry(),
            address.getLatitude(),
            address.getLongitude()
        );
    }
}
