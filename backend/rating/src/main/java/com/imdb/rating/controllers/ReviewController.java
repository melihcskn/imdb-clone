package com.imdb.rating.controllers;

import com.imdb.rating.messaging.RabbitMQProducer;
import com.imdb.rating.models.Requests.ReviewRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {

    RabbitMQProducer rabbitMQProducer;

    ReviewController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @PostMapping(path = "/addReview")
    @ResponseBody
    @PreAuthorize("hasRole('imdb_user')")
    public ResponseEntity<?> addReview(@Valid @RequestBody ReviewRequest reviewRequest) {
        try {
            return rabbitMQProducer.sendMessage(reviewRequest, ReviewRequest.ReviewType.REVIEW);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/addRating")
    @ResponseBody
    @PreAuthorize("hasRole('imdb_user')")
    public ResponseEntity<?> addRating(@Valid @RequestBody ReviewRequest ratingRequest){
        try{
            return rabbitMQProducer.sendMessage(ratingRequest,ReviewRequest.ReviewType.RATING);
        } catch (Throwable t){
            return new ResponseEntity<>(t.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/addTest")
    @ResponseBody
    public ResponseEntity<?> addTest(@Valid @RequestBody ReviewRequest testRequest){
        try{
            return rabbitMQProducer.sendMessage(testRequest,ReviewRequest.ReviewType.RATING);
        } catch (Throwable t){
            return new ResponseEntity<>(t.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
