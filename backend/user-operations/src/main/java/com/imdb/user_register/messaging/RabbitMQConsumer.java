package com.imdb.user_register.messaging;

import com.imdb.user_register.models.request.ReviewRequest;
import com.imdb.user_register.services.interfaces.ReviewService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMQConsumer {

    ReviewService reviewService;
    static final String ratingQueueName = "imdb.rating.queue";

    RabbitMQConsumer(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //Listen review/rating queues
    @RabbitListener(queues = ratingQueueName)
    public Map<String,String> receive(ReviewRequest review){
        System.out.println(review);
        return reviewService.addReview(review);
    }
}
