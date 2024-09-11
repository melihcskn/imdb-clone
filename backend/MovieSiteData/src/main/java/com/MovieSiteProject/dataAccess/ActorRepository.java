package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer> {

    Actor getByActorId(int actorId);
    List<Actor> getByActorNameStartingWith(String actorName);
    @Query(value ="SELECT a FROM Actor a WHERE CONCAT(a.actorName,' ',a.actorSurname) LIKE %:searchParam%")
    List<Actor> getActorByNameContains(@Param("searchParam") String actorName);
}
