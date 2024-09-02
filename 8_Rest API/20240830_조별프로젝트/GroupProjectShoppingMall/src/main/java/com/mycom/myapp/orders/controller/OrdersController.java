package com.mycom.myapp.orders.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.orders.dto.OrdersResultDto;
import com.mycom.myapp.orders.service.OrdersService;
import com.mycom.myapp.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
	
	private final OrdersService ordersService;
	boolean isCustomer = false;  // 일반회원 권한 유무
	
	// 회원 상품 주문
	@PostMapping("/insertorders")
	public OrdersResultDto insertOrders(
			// @RequestParam("userId") Long userId, 
			@RequestParam("productId") Long productId, 
			@RequestParam("orderQuantity") int orderQuantity,
			HttpSession session
			) {
		OrdersResultDto ordersResultDto = new OrdersResultDto();
		
		Long userId = ( (UserDto)session.getAttribute("userDto") ).getId();
		
		// 일반 회원 확인
		Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
		roles.forEach((key, role) -> {
			if (role.equals("role_customer")) { 
				isCustomer = true;
			}
		});

		if (isCustomer) { // 일반 회원 권한 있는 경우
			ordersResultDto = ordersService.insertOrders(userId, productId, orderQuantity);
		} else { // 일반 회원 권한 없는 경우
			ordersResultDto.setResult("fail");
		}

		isCustomer = false;
		return ordersResultDto;
	}

}
