package com.MovieSiteProject.models.dtos;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieActorsDTO {
    private String actorName;
    private Integer actorId;
    private String actorPoster;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieActorsDTO that)) return false;
        return Objects.equals(actorName, that.actorName) && Objects.equals(actorId, that.actorId) && Objects.equals(actorPoster, that.actorPoster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, actorId, actorPoster);
    }
}
