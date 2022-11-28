package com.example.product_catalog_api.controller;


import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.model.ProductDTO;
import com.example.product_catalog_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;
    @Autowired
    void setProductService(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product addProduct(@Valid @RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@Valid @RequestBody ProductDTO productDTO,@PathVariable Long id){
        return productService.updateProduct(productDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/popularity")
    public List<Product> getProductsByPopularity(){
        return productService.getAllProductsSortedByPopularity();
    }
}
