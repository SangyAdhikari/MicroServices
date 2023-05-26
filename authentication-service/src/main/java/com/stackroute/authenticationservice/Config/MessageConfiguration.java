package com.stackroute.authenticationservice.Config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    private String queue1= "Register-User";
    private String queue2= "Update-User-Password";
    private String queue3= "Register-Organizer";
    private String queue4= "Update-Organizer-Password";



    @Bean
    Queue queue1()
    {
        return new Queue(queue1,false);
    }
    @Bean
    Queue queue2()
    {
        return new Queue(queue2,false);
    }
    @Bean
    Queue queue3()
    {
        return new Queue(queue3,false);
    }
    @Bean
    Queue queue4()
    {
        return new Queue(queue4,false);
    }

    @Bean
    DirectExchange exchange()
    {
        return new DirectExchange("User-Exchange");
    }
    @Bean
    Binding binding(Queue queue1,DirectExchange exchange)
    {
        return org.springframework.amqp.core.BindingBuilder.bind(queue1).to(exchange).with("Register-User");
    }
    @Bean
    Binding binding1(Queue queue2,DirectExchange exchange)
    {
        return org.springframework.amqp.core.BindingBuilder.bind(queue2).to(exchange).with("Update-User-Password");
    }
    @Bean
    Binding binding2(Queue queue3,DirectExchange exchange)
    {
        return org.springframework.amqp.core.BindingBuilder.bind(queue3).to(exchange).with("Register-Organizer");
    }
    @Bean
    Binding binding3(Queue queue4,DirectExchange exchange)
    {
        return org.springframework.amqp.core.BindingBuilder.bind(queue4).to(exchange).with("Update-Organizer-Password");
    }
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}