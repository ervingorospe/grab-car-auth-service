package com.ervingorospe.grab_auth_service.controller;

import com.ervingorospe.grab_auth_service.model.DTO.EmailDTO;
import com.ervingorospe.grab_auth_service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> updateEmail(@RequestBody EmailDTO emailDTO, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateEmail(emailDTO, UUID.fromString(id)));
    }
}
