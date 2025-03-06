package com.ervingorospe.grab_auth_service.service.auth;

import com.ervingorospe.grab_auth_service.handler.error.UserNotFoundException;
import com.ervingorospe.grab_auth_service.model.DTO.AuthRequest;
import com.ervingorospe.grab_auth_service.config.jwt.JwtUtil;
import com.ervingorospe.grab_auth_service.model.DTO.AuthResponse;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final JwtUtil jwtUtil;
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(JwtUtil jwtUtil, UserRepo repository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse authLogin(AuthRequest authRequest) {
        User user = repository.findByEmail(authRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(token, refreshToken, jwtUtil.getExpiration());
    }

    @Override
    public AuthResponse refreshAccessToken(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String email = jwtUtil.getEmailFromToken(refreshToken);
        String token = jwtUtil.generateToken(email);

        return new AuthResponse(token, refreshToken, jwtUtil.getExpiration());
    }
}
