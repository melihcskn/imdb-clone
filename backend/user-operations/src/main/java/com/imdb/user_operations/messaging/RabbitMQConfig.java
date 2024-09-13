package com.imdb.user_operations.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    static final String directExchangeName = "amq.direct";
    //static final String keycloakUserRoutingKey = "KK.EVENT.ADMIN.imdb.SUCCESS.USER.*";
    static final String ratingRoutingKey = "imdb.rating.create";
    static final String ratingQueueName = "imdb.rating.queue";

    @Bean
    public Queue queue() {
        return new Queue(ratingQueueName);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(directExchangeName);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ratingRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
