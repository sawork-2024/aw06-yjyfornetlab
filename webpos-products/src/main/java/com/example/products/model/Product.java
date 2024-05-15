package com.example.products.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable{

    @Id
    private String id;

    private String name;

    private double price;

    private String image;

    public Product(){

    }

    @Override
    public String toString() {
        return getId() + "\t" + getName() + "\t" + getPrice();
    }

}
