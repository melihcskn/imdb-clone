package com.MovieSiteProject.services.implementations;

import com.MovieSiteProject.dataAccess.ActorRepository;
import com.MovieSiteProject.models.entities.Actor;
import com.MovieSiteProject.models.dtos.ActorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActorManagerTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorManager actorManager;

    private final List<Actor> actors;

    private final List<ActorDTO> actorDTOs;

    ActorManagerTest(){
        actors = new ArrayList<>();
        actorDTOs = new ArrayList<>();

        Actor actor1 = Actor.builder()
                .actorName("name")
                .actorSurname("surname")
                .actorBirthDay(new Date(1990,03,07))
                .actorId(1)
                .build();

        Actor actor2 = Actor.builder()
                .actorName("name")
                .actorSurname("surname")
                .actorBirthDay(new Date(1990,03,07))
                .actorId(2)
                .build();

        ActorDTO actorDTO1 = ActorDTO.builder()
                .actorName("name")
                .actorSurname("surname")
                .actorId(1)
                .build();

        ActorDTO actorDTO2 = ActorDTO.builder()
                .actorName("name")
                .actorSurname("surname")
                .actorId(1)
                .build();

        actors.add(actor1);
        actors.add(actor2);

        actorDTOs.add(actorDTO1);
        actorDTOs.add(actorDTO2);
    }

    @Test
    void givenId_whenGetByActorId_thenReturnActorDTO(){
        int actorId = 1;

        when(actorRepository.getByActorId(actorId)).thenReturn(actors.get(0));

        ActorDTO foundActor = actorManager.getByActorId(actorId);

        assertEquals(foundActor.getActorId(),actorId);
    }

    @Test
    void givenActors_whenGetAll_ReturnActorDTOs(){

        when(actorRepository.findAll()).thenReturn(actors);

        List<ActorDTO> foundActors = actorManager.getAll();

        assertFalse(foundActors.isEmpty());
        assertEquals(foundActors.size(),actors.size());
    }

    @Test
    void givenQuery_whenGetByActorNameStartingWith_thenReturnActorDTOs(){
        String query = "name";

        List<Actor> response = new ArrayList<>();

        actors.forEach(data->
        {
            if(data.getActorName().startsWith(query))
                response.add(data);
        }
                );

        when(actorRepository.getByActorNameStartingWith(query)).thenReturn(response);

        List<ActorDTO> foundActors = actorManager.getByActorNameStartingWith(query);

        assertFalse(foundActors.isEmpty());
        foundActors.forEach(data->
                assertTrue(data.getActorName().startsWith(query))
                );
    }

    @Test
    void givenInvalidQuery_whenGetByActorNameStartingWith_thenReturnEmpty(){
        String query = "ame";

        List<Actor> response = new ArrayList<>();

        actors.forEach(data->
                {
                    if(data.getActorName().startsWith(query))
                        response.add(data);
                }
        );

        when(actorRepository.getByActorNameStartingWith(query)).thenReturn(response);

        List<ActorDTO> foundActors = actorManager.getByActorNameStartingWith(query);

        assertTrue(foundActors.isEmpty());
    }

    @Test
    void givenQuery_whenGetActorByNameContains_thenReturnActorDTOs(){
        String query = "name";

        List<Actor> response = new ArrayList<>();

        actors.forEach(data->
                {
                    if(data.getActorName().startsWith(query))
                        response.add(data);
                }
        );

        when(actorRepository.getByActorNameStartingWith(query)).thenReturn(response);

        List<ActorDTO> foundActors = actorManager.getByActorNameStartingWith(query);

        assertFalse(foundActors.isEmpty());
        foundActors.forEach(data->
                assertTrue(data.getActorName().startsWith(query))
        );
    }

    @Test
    void givenInvalidQuery_whenGetActorByNameContains_thenReturnEmpty(){
        String query = "x";

        List<Actor> response = new ArrayList<>();

        actors.forEach(data->
                {
                    if(data.getActorName().startsWith(query))
                        response.add(data);
                }
        );

        when(actorRepository.getByActorNameStartingWith(query)).thenReturn(response);

        List<ActorDTO> foundActors = actorManager.getByActorNameStartingWith(query);

        assertTrue(foundActors.isEmpty());
    }
}