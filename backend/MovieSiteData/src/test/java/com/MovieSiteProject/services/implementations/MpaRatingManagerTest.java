package com.MovieSiteProject.services.implementations;

import com.MovieSiteProject.dataAccess.MpaRatingRepository;
import com.MovieSiteProject.models.entities.MpaRating;
import com.MovieSiteProject.models.dtos.MpaRatingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MpaRatingManagerTest {

    @Mock
    private MpaRatingRepository mpaRatingRepository;
    @InjectMocks
    private MpaRatingManager mpaRatingManager;
    private final List<MpaRating> mpaRatings;
    private final List<MpaRatingDTO> mpaRatingDTOs;

    MpaRatingManagerTest(){
        MpaRating mpaRating1 = MpaRating.builder()
                .mpaRatingId(1)
                .mpaRatingName("test")
                .mpaDescription("testdesc")
                .mpaAbbreviation("tst")
                .build();

        MpaRating mpaRating2 = MpaRating.builder()
                .mpaRatingId(2)
                .mpaRatingName("test")
                .mpaDescription("testdesc")
                .mpaAbbreviation("tst")
                .build();

        MpaRatingDTO mpaRatingDTO1 = MpaRatingDTO.builder()
                .mpaRatingId(1)
                .mpaRatingName("test")
                .mpaDescription("testdesc")
                .mpaAbbreviation("tst")
                .build();

        MpaRatingDTO mpaRatingDTO2 = MpaRatingDTO.builder()
                .mpaRatingId(2)
                .mpaRatingName("test")
                .mpaDescription("testdesc")
                .mpaAbbreviation("tst")
                .build();

        mpaRatings = new ArrayList<>();
        mpaRatingDTOs = new ArrayList<>();

        mpaRatings.add(mpaRating1);
        mpaRatings.add(mpaRating2);

        mpaRatingDTOs.add(mpaRatingDTO1);
        mpaRatingDTOs.add(mpaRatingDTO2);
    }

    @Test
    void givenMpaRatingDTO_whenCreateMpaRating_thenReturnCreatedMpaRatingDTO(){
        MpaRating mpaRating = mpaRatings.get(0);

        MpaRatingDTO mpaRatingDTO = mpaRatingDTOs.get(0);

        when(mpaRatingRepository.save(Mockito.any(MpaRating.class))).thenReturn(mpaRating);

        MpaRatingDTO savedMpaRating = mpaRatingManager.createMpaRating(mpaRatingDTO);

        assertEquals(savedMpaRating,mpaRatingDTO);
    }

}