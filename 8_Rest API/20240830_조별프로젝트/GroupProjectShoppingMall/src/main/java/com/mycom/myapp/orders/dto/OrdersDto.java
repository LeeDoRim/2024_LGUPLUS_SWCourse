package com.mycom.myapp.orders.dto;

import java.time.LocalDate;

import com.mycom.myapp.product.dto.ProductDto;
import com.mycom.myapp.user.dto.UserDto;

import lombok.Data;

@Data
public class OrdersDto {
	
	private int id;
	private UserDto UserDto;
	private ProductDto productDto;
	private int orderQuantity;
	private LocalDate orderDate;

}
