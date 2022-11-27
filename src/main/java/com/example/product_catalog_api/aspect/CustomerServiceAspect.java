package com.example.product_catalog_api.aspect;


import com.example.product_catalog_api.entity.Customer;
import com.example.product_catalog_api.repository.CustomerRepo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

@Aspect
@Component
public class CustomerServiceAspect {
    @Autowired
    private CustomerRepo customerRepo;

    @Before("execution(public * com.example.product_catalog_api.controller.AuthController.registerHandler(..))")
    public void CheckNoRedundancyForName(JoinPoint joinPoint){
        System.out.println("from CheckNoRedundancyForName before method");
        Object args[]=joinPoint.getArgs();

        for(Object x:args){
            System.out.println(x);
            if(x instanceof Customer){
                Customer customer=(Customer) x;
                Customer byEmail= customerRepo.findByEmail(customer.getEmail()).orElse(null);
                if(byEmail!=null){
                    System.out.println("first name needs to be unique");
                    throw new ConstraintViolationException("first email needs to be unique", Collections.emptySet());
                }
            }

        }

    }
}
