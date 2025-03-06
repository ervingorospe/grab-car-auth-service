package com.ervingorospe.grab_auth_service.model.DTO;

import lombok.Getter;

@Getter
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;

    public AuthResponse() {
    }

    public AuthResponse(String accessToken, String refreshToken, long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
}
