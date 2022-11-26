package com.example.product_catalog_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="customer_cart",joinColumns = {@JoinColumn(name="user_id")},
//            inverseJoinColumns = {@JoinColumn(name="cart_id")})
    private List<Cart> carts;

}
