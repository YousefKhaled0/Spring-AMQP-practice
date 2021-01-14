package com.example.publishers;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class FanOutPublisher {
    private RabbitTemplate rabbitTemplate;
    private final String routingKey = "#";
    private final String fanOutExchangeName = "fanOut_exchange";
    private static int messageNumber = 0;

    public FanOutPublisher() {
        rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory());
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend(fanOutExchangeName, routingKey,
                "Message number " + messageNumber++);
    }
}
