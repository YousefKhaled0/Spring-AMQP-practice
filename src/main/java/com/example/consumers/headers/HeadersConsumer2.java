package com.example.consumers.headers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersConsumer2 {

    @RabbitListener(queues = "Hq2")
    private void messageHandler(String msg) {
        System.out.println(HeadersConsumer2.class + " " + msg);
    }

}
