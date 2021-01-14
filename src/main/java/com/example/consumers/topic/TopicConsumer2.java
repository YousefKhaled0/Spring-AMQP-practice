package com.example.consumers.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "tq2")
public class TopicConsumer2 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(TopicConsumer2.class + " " + msg);
    }
}

