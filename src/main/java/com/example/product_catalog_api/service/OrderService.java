package com.example.product_catalog_api.service;

import com.example.product_catalog_api.entity.Order;
import com.example.product_catalog_api.model.OrderDTO;

import java.util.List;


public interface OrderService {

    public Order CreateOrder(OrderDTO orderDTO);

    public List<Order> getMyOrders();

    Order getOrder(Long id);
}
