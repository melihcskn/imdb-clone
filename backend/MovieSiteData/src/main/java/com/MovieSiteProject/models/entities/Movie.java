package com.MovieSiteProject.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "title")
    private String movieName;

    @Column(name = "release_date")
    private Date movieReleaseDate;

    @Column(name = "runtime")
    private Integer movieRuntime;

    @Column(name = "plot")
    private String movieDescription;

    @Column(name = "poster")
    private String moviePoster;

    @Column(name = "trailer")
    private String movieTrailer;

    @ManyToMany
    @JoinTable(name = "movie_actor",
                joinColumns = {@JoinColumn(referencedColumnName = "movie_id")},
                inverseJoinColumns = {@JoinColumn(referencedColumnName = "actor_id")},
                foreignKey = @ForeignKey(name = "fk_movie_actor_movie_id"),
                inverseForeignKey = @ForeignKey(name = "fk_movie_actor_actor_id")
    )
    @JsonIgnore
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_category",
                joinColumns = {@JoinColumn(referencedColumnName = "movie_id")},
                inverseJoinColumns = {@JoinColumn(referencedColumnName = "category_id")},
                foreignKey = @ForeignKey(name = "fk_movie_category_movie_id"),
                inverseForeignKey = @ForeignKey(name = "fk_movie_category_category_id")
    )
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MPA_rating_id",
                foreignKey = @ForeignKey(name = "fk_movie_mpa_rating"))
    private MpaRating mpaRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(movieId, movie.movieId) && Objects.equals(movieName, movie.movieName) && Objects.equals(movieReleaseDate, movie.movieReleaseDate) && Objects.equals(movieRuntime, movie.movieRuntime) && Objects.equals(movieDescription, movie.movieDescription) && Objects.equals(moviePoster, movie.moviePoster) && Objects.equals(movieTrailer, movie.movieTrailer) && Objects.equals(actors, movie.actors) && Objects.equals(categories, movie.categories) && Objects.equals(mpaRating, movie.mpaRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieReleaseDate, movieRuntime, movieDescription, moviePoster, movieTrailer, actors, categories, mpaRating);
    }
}
