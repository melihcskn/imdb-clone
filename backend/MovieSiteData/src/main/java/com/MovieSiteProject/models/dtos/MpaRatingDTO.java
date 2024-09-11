package com.MovieSiteProject.models.dtos;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpaRatingDTO {
    private Integer mpaRatingId;
    private String mpaDescription;
    private String mpaAbbreviation;
    private String mpaRatingName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MpaRatingDTO that)) return false;
        return Objects.equals(mpaRatingId, that.mpaRatingId) && Objects.equals(mpaDescription, that.mpaDescription) && Objects.equals(mpaAbbreviation, that.mpaAbbreviation) && Objects.equals(mpaRatingName, that.mpaRatingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mpaRatingId, mpaDescription, mpaAbbreviation, mpaRatingName);
    }
}
