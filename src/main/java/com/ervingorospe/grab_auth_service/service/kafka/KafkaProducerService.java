package com.ervingorospe.grab_auth_service.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendVerificationEmail(UUID userId, String type) {
        try {
            Map<String, String> message = new HashMap<>();
            message.put("userId", userId.toString());
            message.put("type", type);

            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("email-verification", jsonMessage);
        } catch (Exception e) {
            log.error("Failed to send Kafka message: {}", e.getMessage());
        }
    }
}
