package com.example.consumers.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "FQ2")
public class FanOutConsumer2 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(FanOutConsumer2.class + " " + msg);
    }

}
