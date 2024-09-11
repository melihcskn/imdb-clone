package com.MovieSiteProject.dataAccess;

import com.MovieSiteProject.models.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private List<Category> categories;


    CategoryRepositoryTest(){
        categories = new ArrayList<>();

        Category category1 = Category.builder()
                .categoryId(1)
                .categoryName("test")
                .build();

        Category category2 = Category.builder()
                .categoryId(2)
                .categoryName("test")
                .build();

        categories.add(category1);
        categories.add(category2);
    }

    @Test
    void givenId_whenGetCategoryById_thenReturnCategory(){
        int categoryId = 1;

        categoryRepository.saveAll(categories);

        Category foundCategory = categoryRepository.getCategoryByCategoryId(categoryId);

        assertNotNull(foundCategory);
        assertEquals(foundCategory.getCategoryId(),categoryId);
    }

}