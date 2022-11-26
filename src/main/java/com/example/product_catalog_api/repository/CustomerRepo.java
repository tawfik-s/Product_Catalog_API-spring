package com.example.product_catalog_api.repository;

import com.example.product_catalog_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByEmail(String email);
}
