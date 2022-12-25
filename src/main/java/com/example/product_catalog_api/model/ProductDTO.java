package com.example.product_catalog_api.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    @Size(min = 2, max = 255, message
            = "nameEn must be between 2 and 200 characters")
    private String nameEn;

    @Size(min = 2, max = 255, message
            = "nameAr must be between 2 and 200 characters")
    private String nameAr;

    private Long limitQuantity;

    private Long categoryId;

    @NotNull
    private Long price;

    @NotNull
    private Long quantity;

//    @Size( max = 500, message
//            = "email must be between 4 and 200 characters")
    private String image;

}
