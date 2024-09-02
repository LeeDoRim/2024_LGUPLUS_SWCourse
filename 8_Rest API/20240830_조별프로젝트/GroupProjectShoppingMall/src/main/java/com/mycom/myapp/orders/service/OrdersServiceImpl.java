package com.mycom.myapp.orders.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.mycom.myapp.orders.dto.OrdersResultDto;
import com.mycom.myapp.orders.entity.Orders;
import com.mycom.myapp.orders.repository.OrdersRepository;
import com.mycom.myapp.product.entity.Product;
import com.mycom.myapp.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

	private final OrdersRepository ordersRepository;
	
	// 회원 상품 주문
	@Override
	public OrdersResultDto insertOrders(Long userId, Long productId, int orderQuantity) {
		OrdersResultDto ordersResultDto = new OrdersResultDto();
		
		// userId로 User 조회?
		User user = new User();
		user.setId(userId);
		
		// productId
		Product product = new Product();
		product.setId(productId);
		
		Orders orders = new Orders();
		orders.setUser(user);
		orders.setProduct(product);
		orders.setOrderQuantity(orderQuantity);
		orders.setOrderDate(LocalDate.now());
		
		try {
			ordersRepository.save(orders);
			ordersResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			ordersResultDto.setResult("fail");
		}
		
		return ordersResultDto;
	}

}
