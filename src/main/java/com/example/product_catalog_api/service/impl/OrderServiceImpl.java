package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.model.CartDTO;
import com.example.product_catalog_api.model.CartItemDTO;
import com.example.product_catalog_api.mapper.ProductCartItemMapper;
import com.example.product_catalog_api.entity.Cart;
import com.example.product_catalog_api.entity.Customer;
import com.example.product_catalog_api.entity.CartItem;
import com.example.product_catalog_api.entity.Product;
import com.example.product_catalog_api.repository.CustomerRepo;
import com.example.product_catalog_api.repository.CartItemRepo;
import com.example.product_catalog_api.repository.CartRepo;
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
    private CartItemRepo orderProductRepo;

    @Autowired
    private CartRepo cartRepo;


    @Override
    @Transactional
    public Cart CreateOrder(CartDTO cartDTO)   {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);
        if (customer == null) {
            throw new RuntimeException("customer not found or you are not logged in");
        }
        List<CartItem> cartItemList = new ArrayList<>();

        List<CartItemDTO> productDTOS = cartDTO.getProducts();

        Cart cart = new Cart();
        for (CartItemDTO opd : productDTOS) {
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

            CartItem cartItem = productCartItemMapper.ProductToCartItem(product);

            cartItem.setQuantity(quantity);

            System.out.println("1 " + cartItem);
            orderProductRepo.flush();
            CartItem cartItem2 = orderProductRepo.save(cartItem);
            orderProductRepo.flush();
            System.out.print("2 " + cartItem2);
            cartItemList.add(cartItem2);
        }
        cart.setCartItems(cartItemList); //add to cart

        cart = cartRepo.save(cart);

        List<Cart> cartList = customer.getCarts(); //add to customer
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        cartList.add(cart);
        customer.setCarts(cartList);
        customerRepo.save(customer);
        return cart;
    }

    @Override
    public List<Cart> getMyOrders() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);
        return customer.getCarts();
    }

    @Override
    public Cart getOrder(Long id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);

        return customer.getCarts().stream()
                .filter(cart -> {
                    return cart.getId() == id;
                }).findFirst().orElse(null);
    }
}
