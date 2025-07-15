package com.example.product.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}


