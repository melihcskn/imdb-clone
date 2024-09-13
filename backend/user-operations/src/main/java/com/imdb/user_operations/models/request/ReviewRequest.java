package com.imdb.user_operations.models.request;

import lombok.*;

@Data
public class ReviewRequest {

    private int titleId;

    private int rating;

    private String text;

    private int authorId;
}
