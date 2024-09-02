package com.mycom.myapp.user.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycom.myapp.orders.entity.Orders;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	
	private Map<Long, String> roles = new HashMap<>();
	
	private List<UserAddressDto> addresses = new ArrayList<>();
	
	private List<UserPhoneDto> phones = new ArrayList<>();

	private List<Orders> orders;
	
}
