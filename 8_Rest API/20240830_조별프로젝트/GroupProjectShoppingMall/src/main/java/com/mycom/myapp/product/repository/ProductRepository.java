package com.mycom.myapp.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
