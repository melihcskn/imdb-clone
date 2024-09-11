package com.imdb.user_register.services.implementations;

import com.imdb.user_register.models.Mapper;
import com.imdb.user_register.models.entity.Review;
import com.imdb.user_register.models.entity.User;
import com.imdb.user_register.models.request.ReviewRequest;
import com.imdb.user_register.repositories.ReviewRepository;
import com.imdb.user_register.repositories.UserRepository;
import com.imdb.user_register.services.interfaces.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    UserRepository userRepository;
    Mapper mapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.mapper = new Mapper();
    }

    @Override
    public Map<String, String> addReview(ReviewRequest review) {
        //Get user by user(author) id
        User user = userRepository.findById(review.getAuthorId()).orElse(null);
        //Create response object
        Map<String, String> response = new HashMap<>();

        //If user exist then proceed to review operation
        if (user != null) {
            //Check if user reviewed the same title before
            Review isNewReview = reviewRepository.findByTitleId(review.getTitleId());
            //Create a review object
            Review reviewToSave = mapper.convertReviewRequestToReview(review,user);
            //If user has reviewed same title before, we change the new one's id to replace it in database
            //Otherwise id will be generated automatically
            if(isNewReview != null) {
                reviewToSave.setReviewId(isNewReview.getReviewId());
                //If new review has no text it's a rating
                //To avoid removing last review's text, we put it into new review object
                if(review.getText()== null){
                    reviewToSave.setText(isNewReview.getText());
                }
            }
            try {
                //Save new review to database
                reviewRepository.save(reviewToSave);
                //Set response object
                response.put("Status", String.valueOf(HttpStatus.OK.value()));
                response.put("Message", "Review added successfully");
            } catch (Exception e) {
                //Set response object
                response.put("Status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
                response.put("Message", e.getMessage());
            }
        } else {
            //Set response object
            response.put("Status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
            response.put("Message", "User not found");
        }
        return response;
    }
}
