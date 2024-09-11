package com.MovieSiteProject.services.implementations;

import com.MovieSiteProject.dataAccess.CategoryRepository;
import com.MovieSiteProject.models.entities.Category;
import com.MovieSiteProject.models.dtos.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryManagerTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryManager categoryManager;

    private final List<Category> categories;

    private final List<CategoryDTO> categoryDTOs;

    CategoryManagerTest(){
        categories = new ArrayList<>();
        categoryDTOs = new ArrayList<>();

        Category category1 = Category.builder()
                .categoryId(1)
                .categoryName("test")
                .build();

        Category category2 = Category.builder()
                .categoryId(2)
                .categoryName("test")
                .build();

        CategoryDTO categoryDTO1 = CategoryDTO.builder()
                .categoryId(1)
                .categoryName("test")
                .build();

        CategoryDTO categoryDTO2 = CategoryDTO.builder()
                .categoryId(2)
                .categoryName("test")
                .build();

        categories.add(category1);
        categories.add(category2);

        categoryDTOs.add(categoryDTO1);
        categoryDTOs.add(categoryDTO2);
    }

    @Test
    void givenId_whenGetById_thenReturnCategory(){
        int categoryId = 1;
        Category response = categories.get(0);

        when(categoryRepository.getCategoryByCategoryId(categoryId)).thenReturn(response);

        CategoryDTO foundCategory = categoryManager.getById(categoryId);

        assertEquals(foundCategory.getCategoryId(),categoryId);
    }

}