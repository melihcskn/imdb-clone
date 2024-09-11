package com.MovieSiteProject.models.dtos;

import com.MovieSiteProject.models.entities.Movie;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private List<Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO that)) return false;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(categoryName, that.categoryName) && Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, movies);
    }
}
