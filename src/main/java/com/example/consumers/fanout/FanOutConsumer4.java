package com.example.consumers.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "FQ4")
public class FanOutConsumer4 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(FanOutConsumer4.class + " " + msg);
    }

}
