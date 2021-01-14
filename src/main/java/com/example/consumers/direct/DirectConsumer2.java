package com.example.consumers.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "DQ1")
public class DirectConsumer2 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(DirectConsumer2.class + " " + msg);
    }

}
