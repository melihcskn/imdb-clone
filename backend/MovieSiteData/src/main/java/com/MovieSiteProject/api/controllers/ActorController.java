package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.ActorDTO;
import com.MovieSiteProject.services.abstracts.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/actors")
@CrossOrigin(origins = "http://localhost:5173")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @GetMapping("/{id}")
    public ActorDTO getById(@PathVariable("id") int actorId){
        return actorService.getByActorId(actorId);
    }

    @GetMapping("/getAll")
    public List<ActorDTO> getAll(){
        return actorService.getAll();
    }

    @GetMapping("/getByNameStartingWith")
    public List<ActorDTO> getByActorNameStartingWith(@RequestParam String searchParam){
        return actorService.getByActorNameStartingWith(searchParam);
    }

    @GetMapping("/getByNameContains")
    public List<ActorDTO> getActorByNameContains(@RequestParam String searchParam){
        return actorService.getActorByNameContains(searchParam);
    }

}
