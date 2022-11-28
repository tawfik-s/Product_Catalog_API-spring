package com.example.product_catalog_api.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Size(min = 2, max = 255, message
            = "name must be between 2 and 200 characters")
    private String name;
    private List<Long> productsIds;
}
