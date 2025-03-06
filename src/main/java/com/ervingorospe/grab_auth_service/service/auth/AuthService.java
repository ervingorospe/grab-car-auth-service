package com.ervingorospe.grab_auth_service.service.auth;

import com.ervingorospe.grab_auth_service.model.DTO.AuthRequest;
import com.ervingorospe.grab_auth_service.model.DTO.AuthResponse;

public interface AuthService {
    AuthResponse authLogin(AuthRequest authRequest);
    AuthResponse refreshAccessToken(String refreshToken);
}
