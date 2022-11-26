package com.example.product_catalog_api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //create table to not have duplicate variables normalization rule
    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="cart_cart_item",joinColumns = {@JoinColumn(name="cart_id")},
//            inverseJoinColumns = {@JoinColumn(name="cart_item_id")})
    private List<CartItem> cartItems;
}
