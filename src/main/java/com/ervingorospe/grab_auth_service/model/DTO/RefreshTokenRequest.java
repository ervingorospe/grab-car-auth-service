package com.ervingorospe.grab_auth_service.model.DTO;

import lombok.Getter;

@Getter
public class RefreshTokenRequest {
    private String refreshToken;

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
