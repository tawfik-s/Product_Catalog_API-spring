package com.example.product_catalog_api.controller;

import com.example.product_catalog_api.model.Customer;
import com.example.product_catalog_api.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class CustomerController {


    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/info")
    public Customer getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("inside the get user info method");
        return customerRepo.findByEmail(email).get();

    }


}
