package com.example.publishers;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


public class DirectPublisher {

    private RabbitTemplate rabbitTemplate;
    private final String routingKey = "directKey";
    private final String directExchangeName = "direct_exchange";
    private static int messageNumber = 0;

    public DirectPublisher() {
        rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory());
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend(directExchangeName, routingKey,
                "Message number " + messageNumber++);
    }

}
