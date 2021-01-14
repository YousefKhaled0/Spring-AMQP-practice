package com.example.publishers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.nio.charset.StandardCharsets;

public class HeadersPublisher {
    private RabbitTemplate rabbitTemplate;
    private final String headersExchange = "headers_exchange";
    private static int messageNumber = 0;

    public HeadersPublisher() {
        rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory());
    }

    public void sendMessage() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("q1", "1");
        messageProperties.setContentEncoding("UTF-8");
        String format = String.format("Message body is %s ", messageNumber++);
        rabbitTemplate.send(headersExchange, "NOT",
                new Message(format.getBytes(StandardCharsets.UTF_8),
                        messageProperties));

        MessageProperties messageProperties2 = new MessageProperties();
        messageProperties2.setHeader("q2", "1456465465");
        String format0 = String.format("Message body is %s ", messageNumber++);
        rabbitTemplate.send(headersExchange, "NOT",
                new Message(format0.getBytes(StandardCharsets.UTF_8),
                        messageProperties2));
    }

}
