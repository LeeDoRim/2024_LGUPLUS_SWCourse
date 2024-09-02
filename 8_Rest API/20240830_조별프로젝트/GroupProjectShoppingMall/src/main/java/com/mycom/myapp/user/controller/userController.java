package com.mycom.myapp.user.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.product.dto.ProductResultDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.UserAddress;
import com.mycom.myapp.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/users")
@RequiredArgsConstructor
public class userController {
	
	private final UserService userService;
	boolean isCustomer = false;  // 일반회원 권한 유무
	
	// 로그인
	@PostMapping("/login")
	public UserResultDto login(
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			HttpSession session
	) {
		UserResultDto userResultDto = userService.login(email, password);
		
		if( userResultDto.getResult().equals("success") ) {
			session.setAttribute("userDto", userResultDto.getUserDto());
		}
		
		return userResultDto;
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public UserResultDto login(HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		
		try {
			session.invalidate();
			userResultDto.setResult("success");
		} catch (IllegalStateException e) {
			userResultDto.setResult("fail");
		}
		
		return userResultDto;
	}
	
	// UserInfo
	@GetMapping("/userlist")
	public UserResultDto detailUser(HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		Long userId = ( (UserDto)session.getAttribute("userDto") ).getId();
		
		// 일반 회원 확인
		Map<Long, String> roles = ( (UserDto)session.getAttribute("userDto") ).getRoles();
		roles.forEach( (key, role) -> {
			if(role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if(isCustomer) { // 일반 회원
			userResultDto = userService.detailUser(userId);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}
		
		System.out.println(userResultDto);
		
		isCustomer = false;
		return userResultDto;
	}
	
	// User update
	@PostMapping("/userupdate")
	public UserResultDto updateUser(
			@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session
			) {
		UserResultDto userResultDto = new UserResultDto();
		Long userId = ( (UserDto)session.getAttribute("userDto") ).getId();
		
		// 일반 회원 확인
		Map<Long, String> roles = ( (UserDto)session.getAttribute("userDto") ).getRoles();
		roles.forEach( (key, role) -> {
			if(role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if(isCustomer) { // 일반 회원
			userResultDto = userService.updateUser(userId, email, name, password);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}
		
		isCustomer = false;
		return userResultDto;
	}
	
//	@GetMapping("/listUserAddress")
//	public UserResultDto detailAddress(HttpSession session) {
//		Long id = ( (UserDto)session.getAttribute("userDto") ).getId();
//		return userService.detailAddress(id);
//	}
	
	// Address Info
	@GetMapping("/listUserAddress")
	public UserResultDto detailAddress(HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		Long id = ( (UserDto)session.getAttribute("userDto") ).getId();

		// 일반 회원 확인
		Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
		roles.forEach((key, role) -> {
			if (role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if (isCustomer) { // 일반 회원
			userResultDto = userService.detailAddress(id);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}

		isCustomer = false;
		return userResultDto;
	}
	
	// 주소 추가
	@PostMapping("/addAddress")
	public UserResultDto insertUserAddress(
			@RequestParam("zipCode") String zipCode, 
			@RequestParam("addr1") String addr1,
			@RequestParam("addr2") String addr2,
			HttpSession session
			) {
		UserResultDto userResultDto = new UserResultDto();
		Long id = ( (UserDto)session.getAttribute("userDto") ).getId();
		
		// 일반 회원 확인
		Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
		roles.forEach((key, role) -> {
			if (role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if (isCustomer) { // 일반 회원
			userResultDto = userService.insertAddress(id, zipCode, addr1, addr2);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}

		isCustomer = false;
		return userResultDto;
	}
	
	// Address Info
	@GetMapping("/listUserPhone")
	public UserResultDto detailPhone(HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		Long id = ((UserDto) session.getAttribute("userDto")).getId();

		// 일반 회원 확인
		Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
		roles.forEach((key, role) -> {
			if (role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if (isCustomer) { // 일반 회원
			userResultDto = userService.detailPhone(id);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}

		isCustomer = false;
		return userResultDto;
	}

	// 주소 추가
	@PostMapping("/addPhone")
	public UserResultDto insertPhone(@RequestParam("phone") String phone, HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		Long id = ((UserDto) session.getAttribute("userDto")).getId();

		// 일반 회원 확인
		Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
		roles.forEach((key, role) -> {
			if (role.equals("role_customer")) { // 일반 회원인 경우
				isCustomer = true;
			}
		});

		if (isCustomer) { // 일반 회원
			userResultDto = userService.insertPhone(id, phone);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}

		isCustomer = false;
		return userResultDto;
	}

}
