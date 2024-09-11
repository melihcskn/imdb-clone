package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.MpaRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpaRatingRepository extends JpaRepository<MpaRating,Integer> {
    MpaRating getByMpaRatingId(int mpaRatingId);

    MpaRating findByMpaRatingId(int mpaRatingId);
}
