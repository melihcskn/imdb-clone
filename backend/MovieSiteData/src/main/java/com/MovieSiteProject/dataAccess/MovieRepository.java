package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query(value = "INSERT INTO movie_actor (movies_movie_id,actors_actor_id) VALUES (:movieId,:actorId)",nativeQuery = true)
    Void addActorToMovieById(@Param("movieId") Integer movieId,@Param("actorId") Integer actorId);
    Movie getByMovieId(Integer movieId);
    List<Movie> getByMovieNameStartingWith(String movieName);
    List<Movie> getMovieByMovieNameContains(String movieName);

}
