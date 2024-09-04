package com.mycom.springbootjparoleshop.orders.service;

import com.mycom.springbootjparoleshop.orders.dto.OrdersResultDto;
import com.mycom.springbootjparoleshop.orders.entity.Orders;

import java.util.List;

public interface OrdersService {
	
	// 회원 상품 주문
	OrdersResultDto insertOrders(Long userId, Long productId, int orderQuantity);

	List<Orders> listOrders();

}
