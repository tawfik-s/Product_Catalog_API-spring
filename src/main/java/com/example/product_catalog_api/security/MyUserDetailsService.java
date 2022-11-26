package com.example.product_catalog_api.security;

import com.example.product_catalog_api.model.Customer;
import com.example.product_catalog_api.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

// A UserDetailsService is used to fetch the user details of the user trying to authenticate
// into the application. This is done in the loadUserByUsername method.
// If no such user is found a UsernameNotFoundException is thrown

@Component // Marks this as a component. Now, Spring Boot will handle the creation and management of the MyUserDetailsService Bean
// and you will be able to inject it in other places of your code
public class MyUserDetailsService implements UserDetailsService {

    // Injecting Dependencies
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch User from the DB
        Optional<Customer> userRes = customerRepo.findByEmail(email);
        // No user found
        if(!userRes.isPresent())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        // Return a User Details object using the fetched User information
        Customer customer = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                customer.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); // Sets the role of the found user
    }
}
