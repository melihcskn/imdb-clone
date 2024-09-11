package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.MovieDTO;
import com.MovieSiteProject.services.abstracts.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/movies")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping(path = "/{id}")
    public MovieDTO getMovieById(@PathVariable("id") int movieId){
        return movieService.getByMovieId(movieId);
    }

    @GetMapping(path = "/getMovieStartingWith")
    public ResponseEntity<List<MovieDTO>> getMovieStartingWith(@RequestParam String searchParam){
        return ResponseEntity.ok(movieService.getByMovieNameStartingWith(searchParam));
    }

    @GetMapping(path = "/getMovieContains")
    public List<MovieDTO> getMovieContains(@RequestParam String searchParam){
        return movieService.getMovieByMovieNameContains(searchParam);
    }

    @GetMapping(path = "/getAll")
    public List<MovieDTO> getMovies(){
        return movieService.getAll();
    }
}
