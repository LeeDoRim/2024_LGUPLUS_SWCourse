package com.mycom.springbootjparoleshop.product.repository;

import com.mycom.springbootjparoleshop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    List<Product> findAll();
    void deleteById(Long id);
    Optional<Product> findById(Long id);
}
