package com.imdb.user_operations.messaging;

import com.imdb.user_operations.models.request.ReviewRequest;
import com.imdb.user_operations.services.interfaces.KeycloakHandleService;
import com.imdb.user_operations.services.interfaces.ReviewService;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMQConsumer {

    ReviewService reviewService;
    KeycloakHandleService keycloakHandleService;
    static final String ratingQueueName = "imdb.rating.queue";
    static final String keycloakQueueName = "keycloak.queue";

    RabbitMQConsumer(ReviewService reviewService, KeycloakHandleService keycloakHandleService) {
        this.reviewService = reviewService;
        this.keycloakHandleService = keycloakHandleService;
    }

    //Listen review/rating queues
    @RabbitListener(queues = ratingQueueName)
    public Map<String,String> receive(ReviewRequest review){
        System.out.println(review);
        return reviewService.addReview(review);
    }

    //Listen keycloak queue
    @RabbitListener(queues = keycloakQueueName)
    public void receive(Message message){
        byte[] body = message.getBody();
        keycloakHandleService.HandleKeycloakMessage(new String(body));
    }
}
