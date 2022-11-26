package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.model.Product;
import com.example.product_catalog_api.repository.ProductRepo;
import com.example.product_catalog_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    @Autowired
    void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getAllProductsSortedByPopularity() {
        return productRepo.findAll().stream().sorted((x, y) ->
                (int) (y.getNumOfSoldUnits() - x.getNumOfSoldUnits())
        ).collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product newProduct) {
        newProduct.setNumOfSoldUnits(0L);
        return productRepo.save(newProduct);
    }

    @Override
    public Product updateProduct(Product newProduct, Long id) {
        newProduct.setId(id);
        newProduct.setNumOfSoldUnits(newProduct.getNumOfSoldUnits());
        return productRepo.save(newProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }
}
