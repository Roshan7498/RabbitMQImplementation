package com.roshansproject.rabbitmqdemo.controller;

//import com.roshansproject.rabbitmqdemo.model.User;
import com.roshansproject.rabbitmqdemo.model.Users;
import com.roshansproject.rabbitmqdemo.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {


    private RabbitMQJsonProducer jsonProducer;
    public JsonMessageController(RabbitMQJsonProducer jsonProducer){
        this.jsonProducer=jsonProducer;
    }

    @PostMapping("/publishJson")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Users user) {
    jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("jsonMessage sent to RabbitMQ...");
    }



    }
