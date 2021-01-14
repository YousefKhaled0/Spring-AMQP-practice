package com.example.consumers.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "tq3")
public class TopicConsumer3 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(TopicConsumer3.class + " " + msg);
    }

}
