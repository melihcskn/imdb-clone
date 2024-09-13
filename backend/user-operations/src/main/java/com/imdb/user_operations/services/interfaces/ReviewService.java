package com.imdb.user_operations.services.interfaces;

import com.imdb.user_operations.models.request.ReviewRequest;

import java.util.Map;

public interface ReviewService {
    Map<String,String> addReview(ReviewRequest review);
}
