package com.crud.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}

