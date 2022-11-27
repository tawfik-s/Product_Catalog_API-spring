package com.example.product_catalog_api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    private String nameEn;

    private String nameAr;

    private Long limitQuantity;

    private Long price;

    private Long quantity;

    private String image;

}
