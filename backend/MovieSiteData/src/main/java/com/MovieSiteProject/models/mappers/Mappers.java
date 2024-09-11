package com.MovieSiteProject.models.mappers;

import com.MovieSiteProject.models.entities.Actor;
import com.MovieSiteProject.models.entities.Category;
import com.MovieSiteProject.models.entities.Movie;
import com.MovieSiteProject.models.entities.MpaRating;
import com.MovieSiteProject.models.dtos.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Mappers {

    public MpaRatingDTO convertMpaRatingToDTO(MpaRating mpaRating){
        MpaRatingDTO dto = new MpaRatingDTO();

        dto.setMpaRatingId(mpaRating.getMpaRatingId());
        dto.setMpaRatingName(mpaRating.getMpaRatingName());
        dto.setMpaAbbreviation(mpaRating.getMpaAbbreviation());
        dto.setMpaDescription(mpaRating.getMpaDescription());

        return dto;
    }

    public MpaRating convertMpaRatingDTOToMpaRating(MpaRatingDTO mpaRatingDTO){
        MpaRating mpaRating = new MpaRating();

        mpaRating.setMpaRatingId(mpaRatingDTO.getMpaRatingId());
        mpaRating.setMpaRatingName(mpaRatingDTO.getMpaRatingName());
        mpaRating.setMpaAbbreviation(mpaRatingDTO.getMpaAbbreviation());
        mpaRating.setMpaRatingName(mpaRatingDTO.getMpaRatingName());

        return mpaRating;
    }

    public List<MpaRatingDTO> convertMpaRatingToDTO(List<MpaRating> mpaRatingData){
        List<MpaRatingDTO> dto = new ArrayList<>();

        mpaRatingData.forEach(mpaRating -> dto.add(convertMpaRatingToDTO(mpaRating)));
        return dto;
    }
    public ActorDTO convertActorToDTO(Actor actor){
        ActorDTO dto = new ActorDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actor.getActorBirthDay());

        dto.setActorId(actor.getActorId());
        dto.setActorName(actor.getActorName());
        dto.setActorSurname(actor.getActorSurname());
        dto.setActorBirthDay(calendar);
        dto.setActorBio(actor.getActorBio());
        dto.setMovies(actor.getMovies());
        dto.setActorPoster(actor.getActorPoster());

        return dto;
    }
    public List<ActorDTO> convertActorToDTO(List<Actor> actors){
        List<ActorDTO> dto = new ArrayList<>();

        actors.forEach(actor ->
                dto.add(convertActorToDTO(actor)));
        return dto;
    }

    public MovieDTO convertMovieToDTO(Movie movie){
        MovieDTO dto = new MovieDTO();
        List<MovieActorsDTO> actors = new ArrayList<>();

        dto.setMovieId(movie.getMovieId());
        dto.setMovieName(movie.getMovieName());
        dto.setMovieReleaseDate(movie.getMovieReleaseDate());
        dto.setMovieMpaRating(movie.getMpaRating() != null ? movie.getMpaRating().getMpaAbbreviation() : null);
        dto.setMovieRuntime(movie.getMovieRuntime());
        dto.setMovieTrailer(movie.getMovieTrailer());
        dto.setMovieCategories(movie.getCategories());
        dto.setMoviePoster(movie.getMoviePoster());
        dto.setMovieDescription(movie.getMovieDescription());

        if(movie.getActors() != null) {
            movie.getActors().forEach(actor ->
            {
                String name = actor.getActorName() + " " + actor.getActorSurname();
                Integer id = actor.getActorId();
                String poster = actor.getActorPoster();
                actors.add(MovieActorsDTO.builder().actorName(name).actorId(id).actorPoster(poster).build());
            });

            dto.setMovieActors(actors);
        }

        return dto;
    }

    public List<MovieDTO> convertMovieToDTO(List<Movie> movies){
        List<MovieDTO> dto = new ArrayList<>();

        movies.forEach(movie->dto.add(convertMovieToDTO(movie)));

        return dto;
    }

    public CategoryDTO convertCategoryToDTO(Category category){
        CategoryDTO dto = new CategoryDTO();

        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setMovies(category.getMovies());

        return dto;
    }
}
