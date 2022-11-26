package com.example.product_catalog_api.service;

import com.example.product_catalog_api.dto.CartDTO;
import com.example.product_catalog_api.model.Cart;

import java.util.List;


public interface OrderService {

    public Cart CreateOrder(CartDTO cartDTO);

    public List<Cart> getMyOrders();

    Cart getOrder(Long id);
}
