package com.MovieSiteProject.services.abstracts;

import com.MovieSiteProject.models.dtos.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO getByMovieId(int movieId);
    List<MovieDTO> getAll();
    List<MovieDTO> getByMovieNameStartingWith(String movieName);
    List<MovieDTO> getMovieByMovieNameContains(String movieName);
}
