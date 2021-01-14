package com.example.consumers.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "tq1")
public class TopicConsumer1 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(TopicConsumer1.class + " " + msg);
    }

}
