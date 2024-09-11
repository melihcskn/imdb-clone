package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.ActorDTO;
import com.MovieSiteProject.services.abstracts.ActorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static ActorService actorService;

    private final List<ActorDTO> actorDTOs;

    ActorControllerTest(){
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

        actorDTOs = new ArrayList<>();

        actorDTOs.add(actorDTO1);
        actorDTOs.add(actorDTO2);
    }

    @BeforeAll
    public static void test(){
        if(actorService == null){
            System.out.println("Failed");
        }
    }

    @Test
    void givenId_WhenGetById_thenReturnActorDTO() throws Exception{
        int actorId = 1;
        ActorDTO actorDTO = actorDTOs.get(0);

        when(actorService.getByActorId(actorId)).thenReturn(actorDTO);

        mockMvc.perform(get("/api/actors/{id}",actorId))
                .andExpect(jsonPath("$.actorName",is(actorDTO.getActorName())))
                .andExpect(jsonPath("$.actorId",is(actorDTO.getActorId())))
                .andExpect(jsonPath("$.actorSurname",is(actorDTO.getActorSurname())));
    }

    @Test
    void givenActors_whenGetAll_thenReturnActorDTOs() throws  Exception{
        when(actorService.getAll()).thenReturn(actorDTOs);

        mockMvc.perform(get("/api/actors/getAll"))
                .andExpect(jsonPath("$.size()",is(actorDTOs.size())));
    }

    @Test
    void givenQuery_whenGetByActorNameStartingWith_thenReturnActorDTOs() throws Exception{
        String query = "name";

        List<ActorDTO> response = new ArrayList<>();

        actorDTOs.forEach(data->
                {
                    if(data.getActorName().startsWith(query))
                        response.add(data);
                }
                );

        when(actorService.getByActorNameStartingWith(query)).thenReturn(response);

        mockMvc.perform(get("/api/actors/getByNameStartingWith").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(response.size())));
    }

    @Test
    void givenInvalidQuery_whenGetByActorNameStartingWith_thenReturnEmpty() throws Exception{
        String query = "ame";

        List<ActorDTO> response = new ArrayList<>();

        actorDTOs.forEach(data->
                {
                    if(data.getActorName().startsWith(query))
                        response.add(data);
                }
        );

        when(actorService.getByActorNameStartingWith(query)).thenReturn(response);

        mockMvc.perform(get("/api/actors/getByNameStartingWith").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(response.size())));
    }


    @Test
    void givenQuery_whenGetActorByNameContains_thenReturnActorDTOs() throws Exception{
        String query = "ame";

        List<ActorDTO> response = new ArrayList<>();

        actorDTOs.forEach(data->{
            if(data.getActorName().contains(query) || data.getActorSurname().contains(query))
                response.add(data);
        });

        when(actorService.getActorByNameContains(query)).thenReturn(response);

        mockMvc.perform(get("/api/actors/getByNameContains").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(response.size())));

    }

    @Test
    void givenInvalidQuery_whenGetActorByNameContains_thenReturnEmpty() throws Exception{
        String query = "x";

        List<ActorDTO> response = new ArrayList<>();

        actorDTOs.forEach(data->{
            if(data.getActorName().contains(query) || data.getActorSurname().contains(query))
                response.add(data);
        });

        when(actorService.getActorByNameContains(query)).thenReturn(response);

        mockMvc.perform(get("/api/actors/getByNameContains").param("searchParam",query))
                .andExpect(jsonPath("$.size()",is(response.size())));

    }
}