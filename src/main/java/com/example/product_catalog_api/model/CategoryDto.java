package com.example.product_catalog_api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private List<Long> productsIds;
}
