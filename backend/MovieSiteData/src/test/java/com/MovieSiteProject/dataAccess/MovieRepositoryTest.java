package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private final List<Movie> movies;

    MovieRepositoryTest(){
        movies = new ArrayList<>();

        Movie movie1 = Movie.builder()
                .movieName("name test")
                .movieId(1)
                .build();

        Movie movie2 = Movie.builder()
                .movieName("name test")
                .movieId(2)
                .build();

        movies.add(movie1);
        movies.add(movie2);
    }

    @Test
    void givenMovies_whenGetAll_thenReturnMovies(){
        movieRepository.saveAll(movies);

        List<Movie> foundMovies = movieRepository.findAll();

        assertEquals(foundMovies,movies);
    }

    @Test
    void givenId_whenFindById_thenReturnMovie() {
        int movieId = 1;

        movieRepository.saveAll(movies);

        Movie foundMovie = movieRepository.findById(movieId).get();

        assertNotNull(foundMovie);
        assertEquals(foundMovie.getMovieId(),movieId);

    }

    @Test
    void givenQuery_whenGetByMovieNameStartingWith_thenReturnMovies() {
        String query = "nam";

        movieRepository.saveAll(movies);

        List<Movie> foundMovies = movieRepository.getByMovieNameStartingWith(query);

        assertFalse(foundMovies.isEmpty());
        foundMovies.forEach(data->
                assertTrue(data.getMovieName().startsWith(query))
                );
    }

    @Test
    void givenInvalidQuery_whenGetByMovieNameStartingWith_thenReturnEmpty() {
        String query = "x";

        movieRepository.saveAll(movies);

        List<Movie> foundMovies = movieRepository.getByMovieNameStartingWith(query);

        assertTrue(foundMovies.isEmpty());
    }

    @Test
    void givenQuery_whenGetByMovieNameContains_thenReturnMovies() {
        String query = "ame";

        movieRepository.saveAll(movies);

        List<Movie> foundMovies = movieRepository.getMovieByMovieNameContains(query);

        assertFalse(foundMovies.isEmpty());
        foundMovies.forEach(data->
                data.getMovieName().contains(query)
                );
    }

    @Test
    void givenInvalidQuery_whenGetByMovieNameContains_thenReturnEmpty() {
        String query = "x";

        movieRepository.saveAll(movies);

        List<Movie> foundMovies = movieRepository.getMovieByMovieNameContains(query);

        assertTrue(foundMovies.isEmpty());
    }
}