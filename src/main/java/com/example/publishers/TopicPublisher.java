package com.example.publishers;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class TopicPublisher {

    private RabbitTemplate rabbitTemplate;
    private final String topicExchangeName = "topic_exchange";
    private static int messageNumber = 0;

    public TopicPublisher() {
        rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory());
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend(topicExchangeName, "hello.world.test",
                "Message number hello" + messageNumber++);
        rabbitTemplate.convertAndSend(topicExchangeName, "other.test",
                "Message number other" + messageNumber++);
    }
}
