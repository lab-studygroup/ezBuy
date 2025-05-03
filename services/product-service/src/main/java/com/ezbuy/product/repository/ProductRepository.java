package com.ezbuy.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezbuy.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}