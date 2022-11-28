package com.example.product_catalog_api.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    @Max(200)
    @Min(1)
    @NotNull
    private Long quantity;
}
