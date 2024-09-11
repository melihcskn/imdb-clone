package com.MovieSiteProject.services.implementations;

import com.MovieSiteProject.dataAccess.ActorRepository;
import com.MovieSiteProject.models.entities.Actor;
import com.MovieSiteProject.models.dtos.ActorDTO;
import com.MovieSiteProject.models.mappers.Mappers;
import com.MovieSiteProject.services.abstracts.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorManager implements ActorService {
    private final ActorRepository actorRepository;
    Mappers mappers = new Mappers();;

    @Autowired
    public ActorManager(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    @Override
    public ActorDTO getByActorId(int actorId) {
        Actor actor = actorRepository.getByActorId(actorId);
        return mappers.convertActorToDTO(actor);
    }

    @Override
    public List<ActorDTO> getAll() {
        List<Actor> actors = actorRepository.findAll();
        return mappers.convertActorToDTO(actors);
    }

    @Override
    public List<ActorDTO> getByActorNameStartingWith(String actorName) {
        List<Actor> actors = actorRepository.getByActorNameStartingWith(actorName);
        return mappers.convertActorToDTO(actors);
    }

    @Override
    public List<ActorDTO> getActorByNameContains(String actorName) {
        List<Actor> actors = actorRepository.getActorByNameContains(actorName);
        return mappers.convertActorToDTO(actors);
    }
}
