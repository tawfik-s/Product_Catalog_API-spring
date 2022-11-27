package com.example.product_catalog_api.repository;

import com.example.product_catalog_api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {
}
