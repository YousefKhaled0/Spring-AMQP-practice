package com.example.consumers.headers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersConsumer1 {

    @RabbitListener(queues = "Hq1")
    private void messageHandler(String msg) {
        System.out.println(HeadersConsumer1.class + " " + msg);
    }

}
