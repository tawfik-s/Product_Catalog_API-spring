package com.example.product_catalog_api.mapper;

import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductProductDTOMapper {

    @Mapping(target = "price",source = "productDTO.price")
    @Mapping(target = "nameAr",source = "productDTO.nameAr")
    @Mapping(target = "nameEn",source = "productDTO.nameEn")
    @Mapping(target = "image",source = "productDTO.image")
    @Mapping(target = "quantity",source = "productDTO.quantity")
    @Mapping(target = "limitQuantity",source = "productDTO.limitQuantity")
    public Product ProductDTOToProduct(ProductDTO productDTO);
}
