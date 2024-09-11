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
@Table(name = "actor")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Actor implements Serializable {
    @Id
    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "name")
    private String actorName;

    @Column(name = "surname")
    private String actorSurname;

    @Column(name = "birthday")
    private Date actorBirthDay;

    @Column(name = "bio",
            columnDefinition = "TEXT")
    private String actorBio;

    @Column(name = "poster")
    private String actorPoster;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return Objects.equals(actorId, actor.actorId) && Objects.equals(actorName, actor.actorName) && Objects.equals(actorSurname, actor.actorSurname) && Objects.equals(actorBirthDay, actor.actorBirthDay) && Objects.equals(actorBio, actor.actorBio) && Objects.equals(actorPoster, actor.actorPoster) && Objects.equals(movies, actor.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, actorName, actorSurname, actorBirthDay, actorBio, actorPoster, movies);
    }
}
