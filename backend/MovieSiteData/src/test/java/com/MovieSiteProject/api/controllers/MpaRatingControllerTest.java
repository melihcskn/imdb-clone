package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.MpaRatingDTO;
import com.MovieSiteProject.services.abstracts.MpaRatingService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MpaRatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MpaRatingService mpaRatingService;

    private final List<MpaRatingDTO> mpaRatingDTOs;

   MpaRatingControllerTest(){
        mpaRatingDTOs = new ArrayList<>();

        MpaRatingDTO mpaRatingDTO1 = MpaRatingDTO.builder()
                .mpaRatingId(1)
                .mpaAbbreviation("tst")
                .mpaDescription("testdesc")
                .mpaRatingName("test")
                .build();

        MpaRatingDTO mpaRatingDTO2 = MpaRatingDTO.builder()
                .mpaRatingId(2)
                .mpaAbbreviation("tst")
                .mpaDescription("testdesc")
                .mpaRatingName("test")
                .build();

        mpaRatingDTOs.add(mpaRatingDTO1);
        mpaRatingDTOs.add(mpaRatingDTO2);
    }

    @Test
    public void givenMpaRatingDTOs_whenGetAll_thenReturnMpaRatings() throws Exception {

        when(mpaRatingService.getAll()).thenReturn(mpaRatingDTOs);

        mockMvc.perform(get("/api/mpaRatings/getAll")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(mpaRatingDTOs.size())));

    }

    @Test
    public void givenId_whenGetById_thenReturnMpaRatingDTO() throws Exception{
        int mpaId = 1;
        MpaRatingDTO mpaRatingDTO = mpaRatingDTOs.get(0);

        when(mpaRatingService.getMpaById(mpaId)).thenReturn(mpaRatingDTO);

        mockMvc.perform(get("/api/mpaRatings/{id}",mpaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mpaRatingId",is(mpaRatingDTO.getMpaRatingId())))
                .andExpect(jsonPath("$.mpaRatingName",is(mpaRatingDTO.getMpaRatingName())))
                .andExpect(jsonPath("$.mpaAbbreviation",is(mpaRatingDTO.getMpaAbbreviation())))
                .andExpect(jsonPath("$.mpaDescription",is(mpaRatingDTO.getMpaDescription())));
    }
}