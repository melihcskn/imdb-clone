package com.MovieSiteProject.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "movie_mpa_film_ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MpaRating {
    @Id
    @Column(name = "MPA_rating_id")
    private Integer mpaRatingId;

    @Column(name = "MPA_rating_description")
    private String mpaDescription;

    @Column(name = "MPA_rating_abbreviation")
    private String mpaAbbreviation;

    @Column(name = "MPA_rating_name")
    private String mpaRatingName;

    @OneToMany(mappedBy = "mpaRating")
    @JsonIgnore
    private List<Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MpaRating mpaRating)) return false;
        return Objects.equals(mpaRatingId, mpaRating.mpaRatingId) && Objects.equals(mpaDescription, mpaRating.mpaDescription) && Objects.equals(mpaAbbreviation, mpaRating.mpaAbbreviation) && Objects.equals(mpaRatingName, mpaRating.mpaRatingName) && Objects.equals(movies, mpaRating.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mpaRatingId, mpaDescription, mpaAbbreviation, mpaRatingName, movies);
    }
}
