package com.ervingorospe.grab_auth_service.controller;

import com.ervingorospe.grab_auth_service.service.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {
//    private final KafkaProducerService kafkaProducerService;

//    public TestController(KafkaProducerService kafkaProducerService) {
//        this.kafkaProducerService = kafkaProducerService;
//    }

    @GetMapping("/gateway-security")
    public String TestGatewaySecurity() {
//        kafkaProducerService.sendVerificationEmail("testing", "registration");
        return "Working";
    }
}
