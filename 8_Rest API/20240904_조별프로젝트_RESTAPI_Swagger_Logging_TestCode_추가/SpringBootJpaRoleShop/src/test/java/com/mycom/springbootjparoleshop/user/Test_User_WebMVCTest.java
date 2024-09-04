package com.mycom.springbootjparoleshop.user;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.mycom.springbootjparoleshop.user.controller.UserController;
import com.mycom.springbootjparoleshop.user.dto.UserDto;
import com.mycom.springbootjparoleshop.user.dto.UserResultDto;
import com.mycom.springbootjparoleshop.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class Test_User_WebMVCTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Test
	public void testAddressList() throws Exception {
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
		
		// Mock 설정: userService.detailAddress 호출 시 mockResult 반환
        UserResultDto mockResult = new UserResultDto();
        mockResult.setResult("success"); // 예상 결과 설정
        when(userService.detailAddress(1L)).thenReturn(mockResult);

        // URL로 요청
        this.mockMvc.perform(get("/users/addresses").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success")); // 결과값 검증
	}
	
	@Test
	public void testAddressInsert() throws Exception {
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
		
		// Mock 설정: userService.insertAddress 호출 시 mockResult 반환
        UserResultDto mockResult = new UserResultDto();
        mockResult.setResult("success"); // 예상 결과 설정
        when(userService.insertAddress(1L, "111", "111", "111")).thenReturn(mockResult);
        
        // URL로 요청
        this.mockMvc.perform( post("/users/addresses")
        						.param("zipCode", "111")
        						.param("addr1", "111")
        						.param("addr2", "111")
        						.session(session) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success")); // 결과값 검증
		
	}
	
	@Test
	public void testPhoneList() throws Exception {
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
		
		// Mock 설정: userService.detailPhone 호출 시 mockResult 반환
        UserResultDto mockResult = new UserResultDto();
        mockResult.setResult("success"); // 예상 결과 설정
        when(userService.detailPhone(1L)).thenReturn(mockResult);

        // URL로 요청
        this.mockMvc.perform(get("/users/phones").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success")); // 결과값 검증
	}
	
	@Test
	public void testPhoneInsert() throws Exception {
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
		
		// Mock 설정: userService.insertPhone 호출 시 mockResult 반환
        UserResultDto mockResult = new UserResultDto();
        mockResult.setResult("success"); // 예상 결과 설정
        when(userService.insertPhone(1L, "01010101010")).thenReturn(mockResult);
        
        // URL로 요청
        this.mockMvc.perform( post("/users/phones")
        						.param("phone", "01010101010")
        						.session(session) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success")); // 결과값 검증
		
	}

}
