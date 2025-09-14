package com.roshansproject.rabbitmqdemo.controller;


import com.roshansproject.rabbitmqdemo.config.RabbitMQConfig;
import com.roshansproject.rabbitmqdemo.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MessageController {


    private RabbitMQProducer producer;
    public MessageController (RabbitMQProducer producer){
        this.producer=producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("message must not be empty");
        }
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ...");
    }



    }
