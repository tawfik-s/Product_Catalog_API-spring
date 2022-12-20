package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.entity.Category;
import com.example.product_catalog_api.repository.CategoryRepo;
import com.example.product_catalog_api.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Resource
    CategoryService categoryService;

    @Resource
    CategoryRepo categoryRepo;

    @Test
    void addCategory() {
        Category category=new Category();
        category.setId(1l);
        category.setName("food");
        Category category2=categoryService.addCategory("food");
        assertEquals(category.getId(),category2.getId());
        assertEquals(category.getName(),category2.getName());

    }
}