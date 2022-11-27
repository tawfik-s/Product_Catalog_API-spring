package com.example.product_catalog_api.mapper;

import com.example.product_catalog_api.entity.CartItem;
import com.example.product_catalog_api.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductCartItemMapper {

    @Mapping(target = "price",source = "product.price")
    @Mapping(target = "nameAr",source = "product.nameAr")
    @Mapping(target = "nameEn",source = "product.nameEn")
    @Mapping(target = "image",source = "product.image")
    public CartItem ProductToCartItem(Product product);
}
