package com.example.product_catalog_api.service.impl;

import com.example.product_catalog_api.dto.CartDTO;
import com.example.product_catalog_api.dto.CartItemDTO;
import com.example.product_catalog_api.mapper.ProductCartItemMapper;
import com.example.product_catalog_api.model.Cart;
import com.example.product_catalog_api.model.Customer;
import com.example.product_catalog_api.model.CartItem;
import com.example.product_catalog_api.model.Product;
import com.example.product_catalog_api.repository.CustomerRepo;
import com.example.product_catalog_api.repository.CartItemRepo;
import com.example.product_catalog_api.repository.CartRepo;
import com.example.product_catalog_api.repository.ProductRepo;
import com.example.product_catalog_api.service.OrderService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public Cart CreateOrder(CartDTO cartDTO) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(email);

        Customer customer = customerRepo.findByEmail(email).orElse(null);
        System.out.println(customer);
        if (customer == null) {
            return null;
        }
        List<CartItem> cartItemList = new ArrayList<>();

        List<CartItemDTO> productDTOS = cartDTO.getProducts();

        Cart cart = new Cart();
        for (CartItemDTO opd : productDTOS) {
            Long id = opd.getId();
            Long quantity = opd.getQuantity();

            Product product = productRepo.findById(id).orElse(null);
            if (product == null || product.getQuantity() < quantity) {
                return null;
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
        cart.setCartItems(cartItemList);

        cart = cartRepo.save(cart);

        List<Cart> cartList = customer.getCarts();
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
