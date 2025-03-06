package com.ervingorospe.grab_auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/gateway-security")
    public String TestGatewaySecurity() {
        return "Working";
    }
}
