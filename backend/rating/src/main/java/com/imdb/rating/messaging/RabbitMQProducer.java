package com.imdb.rating.messaging;

import com.imdb.rating.models.Requests.ReviewRequest;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;
    static final String routingKey = "imdb.rating.create";

    public RabbitMQProducer(RabbitTemplate rabbitTemplate,DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    //Send review to related service via rabbitmq
    public ResponseEntity<?> sendMessage(ReviewRequest reviewRequest, ReviewRequest.ReviewType reviewType){

        switch(reviewType){
            case REVIEW:
                if(reviewRequest.getText() == null || reviewRequest.getText().isEmpty() || reviewRequest.getText().length() >= 400){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Review text should contain at least 1 and maximum 400 characters");
                }
                break;
            case RATING:
                if(reviewRequest.getText() != null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rating requests should not contain a review text");
                }
                break;
        }

        //Prepare rabbit template and response
        //This is a remote procedure call(RPC) that waits for response, therefore it's an async method
        Map<String,String> response = (Map<String, String>) rabbitTemplate.convertSendAndReceive
                (
                        exchange.getName(),
                        routingKey,
                        reviewRequest
                );

        //Extract status and message from response
        HttpStatus status = (HttpStatus.valueOf(Integer.parseInt(response.get("Status"))));
        String message = response.get("Message");

        return new ResponseEntity<>(message,status);
    }
}
