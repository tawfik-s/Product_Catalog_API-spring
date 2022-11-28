package com.example.product_catalog_api.controller;

import com.example.product_catalog_api.entity.Category;
import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.model.CategoryDTO;
import com.example.product_catalog_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories( ){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category addCategory(@Valid @RequestBody CategoryDTO categoryDto){
        return categoryService.addCategory(categoryDto.getName());
    }

//    @PostMapping("/addProductToCategory/{categoryId}/{productId}")
//    public Category addCategoryToProduct(@PathVariable("categoryId") Long categoryId,
//                                         @PathVariable("productId") Long productId){
//        return categoryService.addProductToCategory(productId,categoryId);
//    }

    @GetMapping("/{id}")
    public List<Product> getAllProductsInCategory(@PathVariable Long id){
        return categoryService.getAllProductsInCategory(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }


}
