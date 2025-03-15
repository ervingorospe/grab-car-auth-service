package com.ervingorospe.grab_auth_service.controller;

import com.ervingorospe.grab_auth_service.model.DTO.*;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.service.auth.AuthService;
import com.ervingorospe.grab_auth_service.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authLogin(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> userRegister(@Valid @RequestBody UserRegistrationDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> userRegister(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshAccessToken(request.getRefreshToken()));
    }
}
