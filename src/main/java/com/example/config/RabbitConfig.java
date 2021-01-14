package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.host}")
    private String host;

    @Value("${rabbit.port}")
    private String port;

    @Value("${rabbit.username}")
    private String username;

    @Value("${rabbit.username}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory =
                new CachingConnectionFactory();
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(Integer.parseInt(port));
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(@Autowired ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(@Autowired ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        this.declareDirectExchangeAndQueues(rabbitAdmin);
        this.declareFanOutExchangeAndQueues(rabbitAdmin);
        this.declareTopicExchangeAndQueues(rabbitAdmin);
        this.declareHeadersExchangeAndQueues(rabbitAdmin);
        return rabbitAdmin;
    }

    private void declareDirectExchangeAndQueues(RabbitAdmin rabbitAdmin) {
        DirectExchange directExchange = new DirectExchange("direct_exchange"
                , true, false);
        Queue dq1 = new Queue("DQ1");
        Queue dq2 = new Queue("DQ2");
        String routingKey = "directKey";
        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareQueue(dq1);
        rabbitAdmin.declareQueue(dq2);
        rabbitAdmin.declareBinding(BindingBuilder.bind(dq1)
                .to(directExchange).with(routingKey));
        rabbitAdmin.declareBinding(BindingBuilder.bind(dq2)
                .to(directExchange).with(routingKey));
    }

    private void declareFanOutExchangeAndQueues(RabbitAdmin rabbitAdmin) {
        FanoutExchange fanoutExchange = new FanoutExchange("fanOut_exchange"
                , true, false);
        Queue fq1 = new Queue("FQ1");
        Queue fq2 = new Queue("FQ2");
        Queue fq3 = new Queue("FQ3");
        Queue fq4 = new Queue("FQ4");
        rabbitAdmin.declareExchange(fanoutExchange);
        rabbitAdmin.declareQueue(fq1);
        rabbitAdmin.declareQueue(fq2);
        rabbitAdmin.declareQueue(fq3);
        rabbitAdmin.declareQueue(fq4);
        rabbitAdmin.declareBinding(BindingBuilder.bind(fq1)
                .to(fanoutExchange));
        rabbitAdmin.declareBinding(BindingBuilder.bind(fq2)
                .to(fanoutExchange));
        rabbitAdmin.declareBinding(BindingBuilder.bind(fq3)
                .to(fanoutExchange));
        rabbitAdmin.declareBinding(BindingBuilder.bind(fq4)
                .to(fanoutExchange));
    }

    private void declareTopicExchangeAndQueues(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange("topic_exchange"
                , true, false);
        Queue tq1 = new Queue("tq1");
        Queue tq2 = new Queue("tq2");
        Queue tq3 = new Queue("tq3");
        rabbitAdmin.declareExchange(topicExchange);
        rabbitAdmin.declareQueue(tq1);
        rabbitAdmin.declareQueue(tq2);
        rabbitAdmin.declareQueue(tq3);
        rabbitAdmin.declareBinding(BindingBuilder.bind(tq1)
                .to(topicExchange).with("hello.#"));
        rabbitAdmin.declareBinding(BindingBuilder.bind(tq2)
                .to(topicExchange).with("hello.world.#"));
        rabbitAdmin.declareBinding(BindingBuilder.bind(tq3)
                .to(topicExchange).with("other.#"));
    }

    private void declareHeadersExchangeAndQueues(RabbitAdmin rabbitAdmin) {
        HeadersExchange headersExchange = new HeadersExchange("headers_exchange"
                , true, false);
        Queue hq1 = new Queue("Hq1");
        Queue hq2 = new Queue("Hq2");
        rabbitAdmin.declareExchange(headersExchange);
        rabbitAdmin.declareQueue(hq1);
        rabbitAdmin.declareQueue(hq2);
        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("q1" , "1");
        rabbitAdmin.declareBinding(BindingBuilder.bind(hq1).to(headersExchange)
                .whereAll(headerValues).match());
        rabbitAdmin.declareBinding(BindingBuilder.bind(hq2).to(headersExchange)
                .whereAny("q2").exist());
    }

}
