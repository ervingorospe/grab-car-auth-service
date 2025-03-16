package com.ervingorospe.grab_auth_service.model.DTO;


public record AuthResponse(
    String accessToken,
    String refreshToken,
    long expiresIn
) { }
