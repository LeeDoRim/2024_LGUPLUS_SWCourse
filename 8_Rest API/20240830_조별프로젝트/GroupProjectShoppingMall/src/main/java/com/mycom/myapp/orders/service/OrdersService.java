package com.mycom.myapp.orders.service;

import com.mycom.myapp.orders.dto.OrdersResultDto;

public interface OrdersService {
	
	// 회원 상품 주문
	OrdersResultDto insertOrders(Long userId, Long productId, int orderQuantity);

}
