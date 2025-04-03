package com.ervingorospe.grab_auth_service.controller;

import com.ervingorospe.grab_auth_service.model.DTO.EmailDTO;
import com.ervingorospe.grab_auth_service.model.DTO.PasswordDTO;
import com.ervingorospe.grab_auth_service.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/update")
public class UpdateController {
    private UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<Map<String, String>> updateEmail(@RequestBody @Valid  EmailDTO emailDTO, @PathVariable String id) {
        String message = userService.updateEmail(emailDTO, UUID.fromString(id));
        return ResponseEntity.ok(Map.of("message", message));
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<Map<String, String>> updatePassword(@RequestBody @Valid PasswordDTO passwordDTO, @PathVariable String id) {
        String message = userService.updatePassword(passwordDTO, UUID.fromString(id));
        return ResponseEntity.ok(Map.of("message", message));
    }
}
