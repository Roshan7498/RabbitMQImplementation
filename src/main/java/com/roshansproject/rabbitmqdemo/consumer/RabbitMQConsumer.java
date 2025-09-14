package com.roshansproject.rabbitmqdemo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    // Listener for Queue_Demo (from application.properties)
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeFromAppQueue(String message) {
        LOGGER.info("Consumed from app queue: {}", message);
    }

    // Listener for guiderabbitmq_queue (hardcoded name)
    @RabbitListener(queues = "guiderabbitmq_queue")
    public void consumeFromGuideQueue(String message) {
        LOGGER.info("Consumed from guide queue: {}", message);
    }
}
