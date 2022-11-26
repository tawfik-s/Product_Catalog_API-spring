package com.example.product_catalog_api.service;

import com.example.product_catalog_api.model.Product;

import java.util.List;

public interface ProductService {

   public List<Product> getAllProducts();

   public Product addProduct(Product newProduct);

   public Product updateProduct(Product newProduct,Long id);

   public void deleteProduct(Long productId);

   public List<Product> getAllProductsSortedByPopularity();

}
