package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.entity.OrderItem;
import com.example.product_catalog_api.model.OrderDTO;
import com.example.product_catalog_api.model.OrderItemDTO;
import com.example.product_catalog_api.mapper.ProductCartItemMapper;
import com.example.product_catalog_api.entity.Order;
import com.example.product_catalog_api.entity.Customer;
import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.repository.CustomerRepo;
import com.example.product_catalog_api.repository.OrderItemRepo;
import com.example.product_catalog_api.repository.OrderRepo;
import com.example.product_catalog_api.repository.ProductRepo;
import com.example.product_catalog_api.service.OrderService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;

    private ProductCartItemMapper productCartItemMapper= Mappers.getMapper(ProductCartItemMapper.class);

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private OrderRepo orderRepo;


    @Override
    @Transactional
    public Order CreateOrder(OrderDTO orderDTO)   {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);
        if (customer == null) {
            throw new RuntimeException("customer not found or you are not logged in");
        }
        List<OrderItem> orderItemList = new ArrayList<>();

        List<OrderItemDTO> productDTOS = orderDTO.getProducts();

        Order order = new Order();
        for (OrderItemDTO opd : productDTOS) {
            Long id = opd.getId();
            Long quantity = opd.getQuantity();

            Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("can't find product"));
            if (product.getQuantity() < quantity) {
                throw new RuntimeException("there is no Quantity available");
            } else if (product.getLimitQuantity()<quantity) {
                throw new RuntimeException("you exceed the quantity limit");
            }

            product.setQuantity(product.getQuantity() - quantity);
            product.setNumOfSoldUnits(product.getNumOfSoldUnits()+quantity);
            productRepo.saveAndFlush(product);

            OrderItem orderItem = productCartItemMapper.ProductToCartItem(product);

            orderItem.setQuantity(quantity);

            System.out.println("1 " + orderItem);
            orderItemRepo.flush();
            OrderItem orderItem2 = orderItemRepo.save(orderItem);
            orderItemRepo.flush();
            System.out.print("2 " + orderItem2);
            orderItemList.add(orderItem2);
        }
        order.setOrderItems(orderItemList); //add to cart

        order = orderRepo.save(order);

        List<Order> orderList = customer.getOrders(); //add to customer
        if (orderList == null) {
            orderList = new ArrayList<>();
        }

        orderList.add(order);
        customer.setOrders(orderList);
        customerRepo.save(customer);
        return order;
    }

    @Override
    public List<Order> getMyOrders() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);
        return customer.getOrders();
    }

    @Override
    public Order getOrder(Long id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);

        return customer.getOrders().stream()
                .filter(cart -> {
                    return cart.getId() == id;
                }).findFirst().orElse(null);
    }
}
