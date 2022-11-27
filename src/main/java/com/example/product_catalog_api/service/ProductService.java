package com.example.product_catalog_api.service;

import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.model.ProductDTO;

import java.util.List;

public interface ProductService {

   public List<Product> getAllProducts();

   public Product addProduct(ProductDTO productDTO);

   public Product updateProduct(ProductDTO productDTO,Long id);

   public void deleteProduct(Long productId);

   public List<Product> getAllProductsSortedByPopularity();

}
