package com.example.product_catalog_api.controller;

import com.example.product_catalog_api.dto.CartDTO;
import com.example.product_catalog_api.model.Cart;
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
    public Cart addOrder(@RequestBody CartDTO cartDTO){
        return orderService.CreateOrder(cartDTO);
    }

    @GetMapping("/")
    public List<Cart> getCarts(){
        return orderService.getMyOrders();
    }

    @GetMapping("/{id}")
    public Cart getSingleCart(@PathVariable Long id){
        return orderService.getOrder(id);
    }

}
