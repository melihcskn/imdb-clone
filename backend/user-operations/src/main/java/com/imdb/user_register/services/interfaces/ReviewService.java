package com.imdb.user_register.services.interfaces;

import com.imdb.user_register.models.request.ReviewRequest;
import java.util.Map;

public interface ReviewService {
    Map<String,String> addReview(ReviewRequest review);
}
