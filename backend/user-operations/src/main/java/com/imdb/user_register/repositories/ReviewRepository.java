package com.imdb.user_register.repositories;

import com.imdb.user_register.models.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByTitleId(int titleId);
}
