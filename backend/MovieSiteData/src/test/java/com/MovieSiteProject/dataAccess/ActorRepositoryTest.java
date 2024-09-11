package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ActorRepositoryTest {
    @Autowired
    private ActorRepository actorRepository;
    private final List<Actor> actorList;

    ActorRepositoryTest(){
        Actor actor1 = Actor.builder()
                .actorId(1)
                .actorName("name")
                .actorSurname("surname")
                .build();

        Actor actor2 = Actor.builder()
                .actorId(2)
                .actorName("name")
                .actorSurname("surname")
                .build();

        actorList = new ArrayList<>();

        actorList.add(actor1);
        actorList.add(actor2);
    }

    @Test
    void givenId_whenGetByActorId_thenReturnActor(){
        int actorId = 1;

        actorRepository.saveAll(actorList);

        Actor foundActor = actorRepository.getByActorId(actorId);

        assertNotNull(foundActor);
        assertEquals(foundActor.getActorId(),actorId);
    }

    @Test
    void givenQuery_whenGetByActorNameStartingWith_thenReturnActors(){
        String query = "name";

        actorRepository.saveAll(actorList);

        List<Actor> foundActors = actorRepository.getByActorNameStartingWith(query);

        assertNotNull(foundActors);
        foundActors.forEach(data ->
                assertTrue(data.getActorName().startsWith(query))
                );
    }

    @Test
    void givenQuery_whenGetActorByNameContains_thenReturnActors(){
        String query  = "ame";

        actorRepository.saveAll(actorList);

        List<Actor> foundActors = actorRepository.getActorByNameContains(query);

        assertFalse(foundActors.isEmpty());
        foundActors.forEach(data->{
            assertTrue(data.getActorName().contains(query) || data.getActorSurname().contains(query));
        });
    }
}