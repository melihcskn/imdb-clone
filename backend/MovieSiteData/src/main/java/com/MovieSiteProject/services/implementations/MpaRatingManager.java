package com.MovieSiteProject.services.implementations;

import com.MovieSiteProject.dataAccess.MpaRatingRepository;
import com.MovieSiteProject.models.entities.MpaRating;
import com.MovieSiteProject.models.dtos.MpaRatingDTO;
import com.MovieSiteProject.models.mappers.Mappers;
import com.MovieSiteProject.exceptions.NotFoundException;
import com.MovieSiteProject.services.abstracts.MpaRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MpaRatingManager implements MpaRatingService {
    private final MpaRatingRepository mpaRatingRepository;

    @Autowired
    public MpaRatingManager(MpaRatingRepository mpaRatingRepository){
        this.mpaRatingRepository = mpaRatingRepository;
    }

    @Override
    public MpaRatingDTO getMpaById(int mpaRatingId) {
        MpaRating mpaRating = mpaRatingRepository.getByMpaRatingId(mpaRatingId);
        if(mpaRating == null){
            throw new NotFoundException("Rating id is not correct");
        }

        Mappers mappers = new Mappers();
        return mappers.convertMpaRatingToDTO(mpaRatingRepository.getByMpaRatingId(mpaRatingId));
    }

    @Override
    public List<MpaRatingDTO> getAll(){

        Mappers mappers = new Mappers();
        return mappers.convertMpaRatingToDTO(mpaRatingRepository.findAll());
    }

    @Override
    public MpaRatingDTO createMpaRating(MpaRatingDTO mpaRatingDTO){
        Mappers mappers = new Mappers();
        MpaRating mpaRating = mpaRatingRepository.save(mappers.convertMpaRatingDTOToMpaRating(mpaRatingDTO));

        return mappers.convertMpaRatingToDTO(mpaRating);
    }
}
