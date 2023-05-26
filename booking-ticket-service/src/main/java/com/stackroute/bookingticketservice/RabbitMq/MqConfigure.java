package com.stackroute.bookingticketservice.RabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfigure {

    public static final String QUEUE1="email-booking-consumer",QUEUE2="email-cancellation-consumer";
    public static final String EXCHANGE="booking-cancellation-service-producer";
    public static final String ROUTING_KEY1="bookingKey",ROUTING_KEY2="cancellationKey";

    @Bean
    public Queue queue1(){
        return new Queue(QUEUE1);
    }

    @Bean
    public Queue queue2(){ return new Queue(QUEUE2); }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange){
        return BindingBuilder.bind(queue1())
                .to(exchange)
                .with(ROUTING_KEY1);
    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange){
        return BindingBuilder.bind(queue2())
                .to(exchange)
                .with(ROUTING_KEY2);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
