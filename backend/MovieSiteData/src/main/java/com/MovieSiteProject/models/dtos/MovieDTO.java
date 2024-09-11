package com.MovieSiteProject.models.dtos;

import com.MovieSiteProject.models.entities.Category;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Integer movieId;
    private String movieName;
    private String movieDescription;
    private Date movieReleaseDate;
    private String movieMpaRating;
    private String moviePoster;
    private Integer movieRuntime;
    private String movieTrailer;
    private List<MovieActorsDTO> movieActors;
    private List<Category> movieCategories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDTO movieDTO)) return false;
        return Objects.equals(movieId, movieDTO.movieId) && Objects.equals(movieName, movieDTO.movieName) && Objects.equals(movieDescription, movieDTO.movieDescription) && Objects.equals(movieReleaseDate, movieDTO.movieReleaseDate) && Objects.equals(movieMpaRating, movieDTO.movieMpaRating) && Objects.equals(moviePoster, movieDTO.moviePoster) && Objects.equals(movieRuntime, movieDTO.movieRuntime) && Objects.equals(movieTrailer, movieDTO.movieTrailer) && Objects.equals(movieActors, movieDTO.movieActors) && Objects.equals(movieCategories, movieDTO.movieCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieDescription, movieReleaseDate, movieMpaRating, moviePoster, movieRuntime, movieTrailer, movieActors, movieCategories);
    }
}
