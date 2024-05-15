package com.example.carts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.example.carts.model.Cart;

public interface CartRepository extends Repository<Cart,Integer>{
    
    Cart save(Cart cart);

    Optional<Cart> findById(Integer id);

    List<Cart> findAll();
}
