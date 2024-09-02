package com.mycom.myapp.product.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.product.dto.ProductResultDto;
import com.mycom.myapp.product.service.ProductService;
import com.mycom.myapp.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	boolean isCustomer = false;  // 일반회원 권한 유무
	
	// 회원 상품 조회
	@GetMapping("/list")
	public ProductResultDto listAllProduct(HttpSession session) {
		ProductResultDto productResultDto = new ProductResultDto();
		
		// 일반 회원 확인
		Map<Long, String> roles = ( (UserDto)session.getAttribute("userDto") ).getRoles();
		roles.forEach( (key, role) -> {
			if(role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if(isCustomer) { // 일반 회원
			productResultDto = productService.listAllProduct();
		} else {
			productResultDto.setResult("fail");
		}
		
		isCustomer = false;
		return productResultDto;
	}

}
