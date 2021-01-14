package com.example.consumers.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "FQ1")
public class FanOutConsumer1 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(FanOutConsumer1.class + " " + msg);
    }

}
