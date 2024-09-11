package com.MovieSiteProject.services.abstracts;

import com.MovieSiteProject.models.dtos.ActorDTO;

import java.util.List;

public interface ActorService{

    ActorDTO getByActorId(int actorId);
    List<ActorDTO> getAll();
    List<ActorDTO> getByActorNameStartingWith(String actorName);
    List<ActorDTO> getActorByNameContains(String actorName);
}
