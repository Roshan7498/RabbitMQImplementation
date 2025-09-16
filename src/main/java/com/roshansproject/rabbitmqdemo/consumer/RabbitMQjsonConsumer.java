package com.roshansproject.rabbitmqdemo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.roshansproject.rabbitmqdemo.model.Users;

@Service
public class RabbitMQjsonConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger((RabbitMQjsonConsumer.class));

    @RabbitListener(queues={"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(Users user){

        LOGGER.info("Received JSON Message-->>{}",user);

    }
}


