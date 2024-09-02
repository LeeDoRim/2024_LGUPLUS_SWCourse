package com.mycom.myapp.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.orders.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
