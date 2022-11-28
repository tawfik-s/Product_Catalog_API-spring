package com.example.product_catalog_api.service;


import com.example.product_catalog_api.entity.Category;
import com.example.product_catalog_api.entity.Product;

import java.util.List;

public interface CategoryService {

    public Category addCategory(String name);

    public List<Category> getAllCategories();

    public Category addCategory(List<Long> productId,String name);

    public Category addProductToCategory(Long productId,Long categoryId);

    public List<Product> getAllProductsInCategory(Long categoryId);

    void deleteCategory(Long id);
}
