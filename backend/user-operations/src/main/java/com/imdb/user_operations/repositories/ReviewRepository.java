package com.imdb.user_operations.repositories;

import com.imdb.user_operations.models.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByTitleId(int titleId);
}
