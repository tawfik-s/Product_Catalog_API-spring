package com.example.product_catalog_api.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {


    @Size(min = 4, max = 255, message
            = "email must be between 4 and 200 characters")
    private String email;

    @Size(min = 4, max = 255, message
            = "password must be between 4 and 200 characters")
    private String password;

}
