package com.MovieSiteProject.services.implementations;

import com.MovieSiteProject.dataAccess.MovieRepository;
import com.MovieSiteProject.models.entities.Movie;
import com.MovieSiteProject.models.dtos.MovieDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieManagerTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieManager movieManager;

    private final List<Movie> movies;
    private final List<MovieDTO> movieDTOs;

    MovieManagerTest(){
        movies = new ArrayList<>();
        movieDTOs = new ArrayList<>();

        Movie movie1 = Movie.builder()
                .movieName("name test")
                .movieId(1)
                .build();

        Movie movie2 = Movie.builder()
                .movieName("name test")
                .movieId(2)
                .build();

        MovieDTO movieDTO1 = MovieDTO.builder()
                .movieName("name test")
                .movieId(1)
                .build();

        MovieDTO movieDTO2 = MovieDTO.builder()
                .movieName("name test")
                .movieId(2)
                .build();

        movies.add(movie1);
        movies.add(movie2);

        movieDTOs.add(movieDTO1);
        movieDTOs.add(movieDTO2);
    }

    @Test
    void givenId_whenGetByMovieId_thenReturnMovieDTO(){
        int movieId = 1;

        when(movieRepository.getByMovieId(movieId)).thenReturn(movies.get(0));

        MovieDTO foundMovie = movieManager.getByMovieId(movieId);

        assertEquals(foundMovie,movieDTOs.get(0));
    }

    @Test
    void givenMovies_whenGetAll_thenReturnMovieDTOs(){
        when(movieRepository.findAll()).thenReturn(movies);

        List<MovieDTO> foundMovies = movieManager.getAll();

        assertEquals(foundMovies,movieDTOs);
    }

    @Test
    void givenQuery_whenGetByMovieNameStartingWith_thenReturnMovieDTOs(){
        String query = "name";

        List<Movie> response = new ArrayList<>();

        movies.forEach( data -> {
                    if(data.getMovieName().startsWith(query))
                        response.add(data);
                });

        when(movieRepository.getByMovieNameStartingWith(query)).thenReturn(response);

        List<MovieDTO> foundMovies = movieManager.getByMovieNameStartingWith(query);

        foundMovies.forEach(data ->
                assertTrue(data.getMovieName().startsWith(query))
                );
    }

    @Test
    void givenInvalidQuery_whenGetByMovieNameStartingWith_thenReturnEmpty(){
        String query = "test";

        List<Movie> response = new ArrayList<>();

        movies.forEach( data -> {
            if(data.getMovieName().startsWith(query))
                response.add(data);
        });

        when(movieRepository.getByMovieNameStartingWith(query)).thenReturn(response);

        List<MovieDTO> foundMovies = movieManager.getByMovieNameStartingWith(query);

        assertTrue(foundMovies.isEmpty());
    }

    @Test
    void givenQuery_whenGetMovieByMovieNameContains_thenReturnMovieDTOs(){
        String query = "ame";

        List<Movie> response = new ArrayList<>();

        movies.forEach( data -> {
            if(data.getMovieName().contains(query))
                response.add(data);
        });

        when(movieRepository.getMovieByMovieNameContains(query)).thenReturn(response);

        List<MovieDTO> foundMovies = movieManager.getMovieByMovieNameContains(query);

        foundMovies.forEach(data ->
                assertTrue(data.getMovieName().contains(query))
        );
    }

    @Test
    void givenInvalidQuery_whenGetMovieByMovieNameContains_thenReturnEmpty(){
        String query = "x";

        List<Movie> response = new ArrayList<>();

        movies.forEach( data -> {
            if(data.getMovieName().contains(query))
                response.add(data);
        });

        when(movieRepository.getMovieByMovieNameContains(query)).thenReturn(response);

        List<MovieDTO> foundMovies = movieManager.getMovieByMovieNameContains(query);

        assertTrue(foundMovies.isEmpty());
    }
}