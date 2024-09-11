package com.MovieSiteProject.services.abstracts;

import com.MovieSiteProject.models.dtos.CategoryDTO;

public interface CategoryService {

    CategoryDTO getById(int categoryId);
}
