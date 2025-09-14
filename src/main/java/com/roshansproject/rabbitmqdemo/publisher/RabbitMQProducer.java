package com.roshansproject.rabbitmqdemo.publisher;


import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER=  LoggerFactory. getLogger(RabbitMQProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;


    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            LOGGER.warn("Attempted to send empty message — ignoring.");
            return;
        }
        LOGGER.info("Publishing message -> {}", message);

        rabbitTemplate.convertAndSend(exchange, routingKey, message, m -> {
            m.getMessageProperties().setContentType("text/plain");
            m.getMessageProperties().setDeliveryMode(org.springframework.amqp.core.MessageDeliveryMode.PERSISTENT);
            return m;
        });
    }

}

