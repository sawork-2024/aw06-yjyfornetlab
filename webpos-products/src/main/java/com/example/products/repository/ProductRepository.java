package com.example.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.example.products.model.Product;

public interface ProductRepository extends Repository<Product,String>{

    Optional<Product> findById(String id);

    Product save(Product product);

    List<Product> findAll();
}
