package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.mapper.ProductProductDTOMapper;
import com.example.product_catalog_api.model.ProductDTO;
import com.example.product_catalog_api.repository.ProductRepo;
import com.example.product_catalog_api.service.ProductService;
import org.mapstruct.factory.Mappers;
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

    private ProductProductDTOMapper productProductDTOMapper = Mappers.getMapper(ProductProductDTOMapper.class);

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getAllProductsSortedByPopularity() {
        return productRepo.findAll()
                .stream()
                .sorted((x, y) ->
                        (int) (y.getNumOfSoldUnits() - x.getNumOfSoldUnits())
                ).collect(Collectors.toList());
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product newProduct = productProductDTOMapper.ProductDTOToProduct(productDTO);
        newProduct.setNumOfSoldUnits(0L);
        return productRepo.save(newProduct);
    }

    @Override
    public Product updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("not correct product id"));
        Product newProduct = productProductDTOMapper.ProductDTOToProduct(productDTO);
        newProduct.setId(id);
        newProduct.setNumOfSoldUnits(product.getNumOfSoldUnits());
        return productRepo.save(newProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }
}
