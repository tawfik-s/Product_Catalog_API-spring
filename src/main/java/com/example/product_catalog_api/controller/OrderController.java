package com.example.product_catalog_api.controller;

import com.example.product_catalog_api.entity.Order;
import com.example.product_catalog_api.model.OrderDTO;
import com.example.product_catalog_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    void setOrderService(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/")
    public Order addOrder(@RequestBody OrderDTO orderDTO){
        return orderService.CreateOrder(orderDTO);
    }

    @GetMapping("/")
    public List<Order> getOrders(){
        return orderService.getMyOrders();
    }

    @GetMapping("/{id}")
    public Order getSingleOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

}
