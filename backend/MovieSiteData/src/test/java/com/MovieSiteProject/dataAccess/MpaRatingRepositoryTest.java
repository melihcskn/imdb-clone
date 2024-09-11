package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.MpaRating;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MpaRatingRepositoryTest {

    @Autowired
    private MpaRatingRepository mpaRatingRepository;

    private final List<MpaRating> mpaRatings;

    MpaRatingRepositoryTest(){
        mpaRatings = new ArrayList<>();

        MpaRating mpaRatingTest1= MpaRating.builder()
                .mpaRatingId(1)
                .mpaRatingName("test1")
                .mpaDescription("testdesc1")
                .mpaAbbreviation("tst1")
                .build();

        MpaRating mpaRatingTest2= MpaRating.builder()
                .mpaRatingId(2)
                .mpaRatingName("test2")
                .mpaDescription("testdesc2")
                .mpaAbbreviation("tst2")
                .build();

        mpaRatings.add(mpaRatingTest1);
        mpaRatings.add(mpaRatingTest2);
    }

    @Test
    void givenMpaRatings_whenGetAll_thenReturnMpaRatings(){
        mpaRatingRepository.saveAll(mpaRatings);

        List<MpaRating> foundMpaRatings = mpaRatingRepository.findAll();

        assertEquals(foundMpaRatings, mpaRatings);
      }

    @Test
    void givenMpaRating_whenFindById_thenReturnMpaRating(){
        MpaRating mpaRating = mpaRatings.get(0);

        mpaRatingRepository.save(mpaRating);

        Optional<MpaRating> foundMpaRating = mpaRatingRepository.findById(mpaRating.getMpaRatingId());

        assertEquals(mpaRating,foundMpaRating.get());
    }

    @Test
    void givenMpaRating_whenSave_thenReturnNotNull(){
        MpaRating mpaRating = mpaRatings.get(0);

        MpaRating savedMpaRating = mpaRatingRepository.save(mpaRating);

        assertNotNull(savedMpaRating);
    }

    @Test
    void givenId_whenDeleteById_thenReturnNull(){
        MpaRating mpaRating = mpaRatings.get(0);

        MpaRating savedMpaRating = mpaRatingRepository.save(mpaRating);

        assertNotNull(savedMpaRating);

        mpaRatingRepository.deleteById(savedMpaRating.getMpaRatingId());

        Optional<MpaRating> foundMpaRating = mpaRatingRepository.findById(savedMpaRating.getMpaRatingId());

        assertTrue(foundMpaRating.isEmpty());
    }
}