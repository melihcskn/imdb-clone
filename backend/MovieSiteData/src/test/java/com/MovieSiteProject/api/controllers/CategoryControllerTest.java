package com.MovieSiteProject.api.controllers;

import com.MovieSiteProject.models.dtos.CategoryDTO;
import com.MovieSiteProject.services.abstracts.CategoryService;
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
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final List<CategoryDTO> categoryDTOs;

    CategoryControllerTest(){
        categoryDTOs = new ArrayList<>();

        CategoryDTO categoryDTO1 = CategoryDTO.builder()
                .categoryId(1)
                .categoryName("test")
                .build();

        CategoryDTO categoryDTO2 = CategoryDTO.builder()
                .categoryId(2)
                .categoryName("test")
                .build();

        categoryDTOs.add(categoryDTO1);
        categoryDTOs.add(categoryDTO2);
    }

    @Test
    void givenId_whenGetById_thenReturnCategory() throws Exception{
        int categoryId = 1;
        CategoryDTO response = categoryDTOs.get(0);

        when(categoryService.getById(categoryId)).thenReturn(response);

        mockMvc.perform(get("/api/categories/{id}",categoryId))
                .andExpect(jsonPath("$.categoryId",is(response.getCategoryId())));
    }
}