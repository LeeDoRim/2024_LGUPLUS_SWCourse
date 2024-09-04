package com.mycom.springbootjparoleshop.orders.dto;

import com.mycom.springbootjparoleshop.product.dto.ProductDto;
import com.mycom.springbootjparoleshop.user.dto.UserDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrdersDto {
	
	private long id;
	private UserDto UserDto;
	private ProductDto productDto;
	private int orderQuantity;
	private LocalDate orderDate;

}
