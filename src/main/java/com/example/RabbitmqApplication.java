package com.example;

import com.example.config.RabbitConfig;
import com.example.consumers.direct.DirectConsumer1;
import com.example.consumers.direct.DirectConsumer2;
import com.example.consumers.direct.DirectConsumer3;
import com.example.consumers.fanout.FanOutConsumer1;
import com.example.consumers.fanout.FanOutConsumer2;
import com.example.consumers.fanout.FanOutConsumer3;
import com.example.consumers.fanout.FanOutConsumer4;
import com.example.consumers.headers.HeadersConsumer1;
import com.example.consumers.headers.HeadersConsumer2;
import com.example.consumers.topic.TopicConsumer1;
import com.example.consumers.topic.TopicConsumer2;
import com.example.consumers.topic.TopicConsumer3;
import com.example.publishers.DirectPublisher;
import com.example.publishers.FanOutPublisher;
import com.example.publishers.HeadersPublisher;
import com.example.publishers.TopicPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Import({RabbitConfig.class,
        DirectConsumer1.class, DirectConsumer2.class, DirectConsumer3.class
        , FanOutConsumer1.class, FanOutConsumer2.class, FanOutConsumer3.class, FanOutConsumer4.class,
        TopicConsumer1.class, TopicConsumer2.class, TopicConsumer3.class, HeadersConsumer1.class,
        HeadersConsumer2.class})
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    @PostConstruct
    private void publishMessages() {
        DirectPublisher directPublisher = new DirectPublisher();
        FanOutPublisher fanOutPublisher = new FanOutPublisher();
        TopicPublisher topicPublisher = new TopicPublisher();
        HeadersPublisher headersPublisher = new HeadersPublisher();
        headersPublisher.sendMessage();
    }

}
