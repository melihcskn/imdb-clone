package com.MovieSiteProject.services.abstracts;

import com.MovieSiteProject.models.dtos.MpaRatingDTO;

import java.util.List;

public interface MpaRatingService {
    MpaRatingDTO getMpaById(int mpaRatingId);

    List<MpaRatingDTO> getAll();

    MpaRatingDTO createMpaRating(MpaRatingDTO mpaRatingDTO);
}
