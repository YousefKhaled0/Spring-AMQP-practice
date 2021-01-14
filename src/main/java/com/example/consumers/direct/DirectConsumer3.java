package com.example.consumers.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "DQ2")
public class DirectConsumer3 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(DirectConsumer3.class + " " + msg);
    }
}
