package com.example.consumers.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "FQ3")
public class FanOutConsumer3 {

    @RabbitHandler
    private void messageHandler(String msg) {
        System.out.println(FanOutConsumer3.class + " " + msg);
    }

}
