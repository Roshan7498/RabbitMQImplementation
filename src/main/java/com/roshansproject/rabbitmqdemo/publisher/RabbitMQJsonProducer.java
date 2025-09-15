package com.roshansproject.rabbitmqdemo.publisher;
import com.roshansproject.rabbitmqdemo.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    private static final Logger LOGGER=  LoggerFactory. getLogger(RabbitMQJsonProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;


    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Users user){
//        LOGGER.info(String.format("json message sent -->> {}",user.toString()));
        LOGGER.info("json message sent -->> {}",user);
        rabbitTemplate.convertAndSend(exchange,routingKey,user);


    }
}
