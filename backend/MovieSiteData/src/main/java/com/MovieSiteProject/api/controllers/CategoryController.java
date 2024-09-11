package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.CategoryDTO;
import com.MovieSiteProject.services.abstracts.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/{id}")
    CategoryDTO getById(@PathVariable("id") int categoryId){
        return categoryService.getById(categoryId);
    }
}
