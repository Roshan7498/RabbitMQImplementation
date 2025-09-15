package com.roshansproject.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String appQueueName;           // e.g. Queue_Demo

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;           // e.g. rabbitDemo_Exchange

    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;             // e.g. routingKey_Demo

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;
    // second queue name - you can also load from properties if you prefer
    private final String guideQueueName = "guiderabbitmq_queue";

    @Bean
    public Queue appQueue() {
        return new Queue(appQueueName, true); // durable
    }

    @Bean
    public Queue guideQueue() {
        return new Queue(guideQueueName, true); // durable
    }
    @Bean
    public Queue jsonqueue (){
        return new Queue (jsonQueue);
    }

    // Use TopicExchange for pattern matching or DirectExchange for exact-match
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName, true, false);
    }

    // Binding appQueue -> exchange with routingKey
    @Bean
    public Binding bindingAppQueue(Queue appQueue, TopicExchange exchange) {
        return BindingBuilder.bind(appQueue).to(exchange).with(routingKey);
    }

    // Binding guideQueue -> exchange with same routingKey (so both get the message)
    @Bean
    public Binding bindingGuideQueue(Queue guideQueue, TopicExchange exchange) {
        return BindingBuilder.bind(guideQueue).to(exchange).with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonqueue())
                .to(exchange())
                .with(routingKey);

    }
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
return rabbitTemplate;

    }
}
