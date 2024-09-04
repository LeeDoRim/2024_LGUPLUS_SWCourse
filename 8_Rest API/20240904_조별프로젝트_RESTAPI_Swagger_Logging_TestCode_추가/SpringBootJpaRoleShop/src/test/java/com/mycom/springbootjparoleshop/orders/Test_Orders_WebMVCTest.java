package com.mycom.springbootjparoleshop.orders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.mycom.springbootjparoleshop.orders.controller.OrdersController;
import com.mycom.springbootjparoleshop.orders.dto.OrdersResultDto;
import com.mycom.springbootjparoleshop.orders.service.OrdersService;
import com.mycom.springbootjparoleshop.user.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(OrdersController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class Test_Orders_WebMVCTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	OrdersService ordersService;
	
	@Test
	public void testOrdersInsert() throws Exception {
		// 세션에 필요한 데이터를 설정하는 MockHttpSession 생성
		MockHttpSession session = new MockHttpSession();

		// UserDto 설정 (테스트를 위해 세션에 저장할 객체 생성)
		UserDto userDto = new UserDto();
		userDto.setId(1L); // 필요한 id 값 설정

		// 사용자 역할 설정
		Map<Long, String> roles = userDto.getRoles();
		roles.put(1L, "role_customer"); // 일반 회원 역할 설정

		// 세션에 userDto 저장
		session.setAttribute("userDto", userDto);
		
		// Mock 설정: ordersService.insertOrders 호출 시 mockResult 반환
        OrdersResultDto mockResult = new OrdersResultDto();
        mockResult.setResult("success"); // 예상 결과 설정
        when(ordersService.insertOrders(1L, 1L, 1)).thenReturn(mockResult);
        
        // URL로 요청
        this.mockMvc.perform( post("/customer/orders")
        						.param("productId", "1")
        						.param("orderQuantity", "1")
        						.session(session) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success")); // 결과값 검증
		
	}
	
}
