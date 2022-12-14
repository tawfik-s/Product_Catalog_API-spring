package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.Exceptions.CategoryNotfoundException;
import com.example.product_catalog_api.Exceptions.ProductNotfoundException;
import com.example.product_catalog_api.entity.Category;
import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.model.ReturnedCategoryDTO;
import com.example.product_catalog_api.repository.CategoryRepo;
import com.example.product_catalog_api.repository.ProductRepo;
import com.example.product_catalog_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;
    @Override
    public Category addCategory(String name) {
        Category category=new Category();
        category.setName(name);
        categoryRepo.save(category);
        return category;
    }

    @Override
    public List<ReturnedCategoryDTO> getAllCategories() {
        List<ReturnedCategoryDTO> result=new ArrayList<>();
        categoryRepo.findAll().stream().forEach((category )->{
            ReturnedCategoryDTO temp=new ReturnedCategoryDTO();
            temp.setId(category.getId());
            temp.setName(category.getName());
            result.add(temp);
        });
         return result;
    }

    @Override
    @Transactional
    public Category addCategory(List<Long> productIds,String name) {
        Category category=new Category();
        category.setName(name);
        List<Product> myProductList=new ArrayList<>();
        for(Long productId: productIds){
            Product pro =productRepo.findById(productId)
                    .orElseThrow(ProductNotfoundException::new);
            myProductList.add(pro);
        }
        category.setProducts(myProductList);
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category addProductToCategory(Long productId, Long categoryId) {
       Category category= categoryRepo.findById(categoryId)
               .orElseThrow(CategoryNotfoundException::new);
       Product pro=productRepo.findById(productId) //check if it's exist
               .orElseThrow(ProductNotfoundException::new);
        category.getProducts().add(pro);
        return category;
    }

    @Override
    public List<Product> getAllProductsInCategory(Long categoryId) {
        Category category= categoryRepo.findById(categoryId)
                .orElseThrow(CategoryNotfoundException::new);
        return category.getProducts();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
