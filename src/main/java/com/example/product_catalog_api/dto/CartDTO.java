package com.example.product_catalog_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private List<CartItemDTO> products;
}
