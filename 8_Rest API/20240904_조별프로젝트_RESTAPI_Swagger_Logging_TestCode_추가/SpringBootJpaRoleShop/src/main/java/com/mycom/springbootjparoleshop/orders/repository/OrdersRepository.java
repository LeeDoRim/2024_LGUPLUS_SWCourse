package com.mycom.springbootjparoleshop.orders.repository;

import com.mycom.springbootjparoleshop.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAll();
}
