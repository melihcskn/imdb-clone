package com.imdb.user_operations.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    static final String directExchangeName = "amq.direct";
    static final String keycloakUserRoutingKey = "KK.EVENT.ADMIN.imdb.SUCCESS.USER.*";
    static final String keycloakExchangeName = "amq.topic";
    static final String keycloakQueueName = "keycloak.queue";
    static final String ratingRoutingKey = "imdb.rating.create";
    static final String ratingQueueName = "imdb.rating.queue";

    //Prepare review queue, exchange and binding

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

    //Prepare keycloak queue, exchange and binding

    @Bean
    public Queue keycloakQueue(){ return new Queue(keycloakQueueName); }

    @Bean
    public TopicExchange keycloakExchange(){ return new TopicExchange(keycloakExchangeName); }

    @Bean
    public Binding bindingKeycloakQueue(){
        return BindingBuilder.bind(keycloakQueue()).to(keycloakExchange()).with(keycloakUserRoutingKey);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
