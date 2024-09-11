package com.imdb.rating.models.Requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    public enum ReviewType{
        RATING,
        RATING_UPDATE,
        REVIEW,
        REVIEW_UPDATE,
    }

    @NotNull(message = "Title id can't be empty")
    int titleId;
    @NotNull(message = "User id can't be empty")
    int authorId;
    @NotNull(message = "Rating can't be null")
    @Min(value = 1, message = "Rating can't be smaller than 1")
    @Max(value = 10, message = "Rating can't be bigger than 10 ")
    int rating;

    private String text;

}
