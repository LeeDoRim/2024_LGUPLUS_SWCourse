package com.mycom.springbootjparoleshop.product;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.mycom.springbootjparoleshop.product.controller.ProductController;
import com.mycom.springbootjparoleshop.product.dto.ProductResultDto;
import com.mycom.springbootjparoleshop.product.service.ProductService;
import com.mycom.springbootjparoleshop.user.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ProductController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class Test_Product_WebMVCTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	
	@Test
	public void testProductList() throws Exception {
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
		
		// Mock 설정: productService.listAllProduct 호출 시 mockResult 반환
		ProductResultDto mockResult = new ProductResultDto();
        mockResult.setResult("success"); // 예상 결과 설정
        when(productService.listAllProduct()).thenReturn(mockResult);

        // URL로 요청
        this.mockMvc.perform(get("/costomer/products").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success")); // 결과값 검증
	}
	
}
