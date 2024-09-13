package com.imdb.user_operations.models;

import com.imdb.user_operations.models.entity.Review;
import com.imdb.user_operations.models.request.RegisterRequest;
import com.imdb.user_operations.models.request.ReviewRequest;
import com.imdb.user_operations.models.dto.UserDTO;
import com.imdb.user_operations.models.entity.User;

public class Mapper {

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserName(user.getUsername());

        return userDTO;
    }

    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUserName());

        return user;
    }

    public User convertRegisterRequestToUser(RegisterRequest registerRequest) {
        User user = new User();

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());

        return user;
    }

    public Review convertReviewRequestToReview(ReviewRequest reviewRequest, User user) {
        Review review = new Review();

        review.setRating(reviewRequest.getRating());
        review.setText(reviewRequest.getText() == null ? "" : reviewRequest.getText());
        review.setTitleId(reviewRequest.getTitleId());
        review.setAuthor(user);

        return review;
    }
}
