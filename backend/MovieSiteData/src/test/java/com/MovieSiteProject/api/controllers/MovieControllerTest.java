package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.MovieDTO;
import com.MovieSiteProject.services.abstracts.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;
    private final List<MovieDTO> movieDTOs;

    MovieControllerTest(){
        movieDTOs = new ArrayList<>();

        MovieDTO movieDTO1 = MovieDTO.builder()
                .movieName("name test")
                .movieId(1)
                .build();

        MovieDTO movieDTO2 = MovieDTO.builder()
                .movieName("name test")
                .movieId(2)
                .build();

        movieDTOs.add(movieDTO1);
        movieDTOs.add(movieDTO2);
    }

    @Test
    public void givenId_whenGetMovieByID_thenReturnMovie() throws Exception{
        int movieId = 1;
        MovieDTO movieDTO = movieDTOs.get(0);

        when(movieService.getByMovieId(movieId)).thenReturn(movieDTO);

        mockMvc.perform(get("/api/movies/{id}",movieId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieName",is(movieDTO.getMovieName())))
                .andExpect(jsonPath("$.movieId",is(movieDTO.getMovieId())));
    }

    @Test
    public void givenMovies_whenGetAll_thenReturnMovieDTOs()throws Exception{
        when(movieService.getAll()).thenReturn(movieDTOs);

        mockMvc.perform(get("/api/movies/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(movieDTOs.size())));
    }

    @Test
    public void givenQuery_whenGetMovieStartingWith_thenReturnMovieDTOs() throws Exception{
        String query = "name";

        List<MovieDTO> response = new ArrayList<>();

        movieDTOs.forEach(data->{
            if(data.getMovieName().startsWith(query))
                response.add(data);
        });

        when(movieService.getByMovieNameStartingWith(query)).thenReturn(response);

        mockMvc.perform(get("/api/movies/getMovieStartingWith").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(response.size())));
    }

    @Test
    public void givenInvalidQuery_whenGetMovieStartingWith_thenReturnEmpty() throws Exception{
        String query = "x";

        List<MovieDTO> response = new ArrayList<>();

        movieDTOs.forEach(data->{
            if(data.getMovieName().startsWith(query))
                response.add(data);
        });

        when(movieService.getByMovieNameStartingWith(query)).thenReturn(response);

        mockMvc.perform(get("/api/movies/getMovieStartingWith").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(0)));
    }

    @Test
    public void givenQuery_whenGetMovieNameContains_thenReturnMovieDTOs() throws Exception{
        String query = "ame";

        List<MovieDTO> response = new ArrayList<>();

        movieDTOs.forEach(data->{
            if(data.getMovieName().contains(query))
                response.add(data);
        });

        when(movieService.getMovieByMovieNameContains(query)).thenReturn(response);

        mockMvc.perform(get("/api/movies/getMovieContains").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(response.size())));
    }

    @Test
    public void givenInvalidQuery_whenGetMovieNameContains_thenReturnEmpty() throws Exception{
        String query = "x";

        List<MovieDTO> response = new ArrayList<>();

        movieDTOs.forEach(data->{
            if(data.getMovieName().contains(query))
                response.add(data);
        });

        when(movieService.getMovieByMovieNameContains(query)).thenReturn(response);

        mockMvc.perform(get("/api/movies/getMovieContains").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(0)));
    }
}