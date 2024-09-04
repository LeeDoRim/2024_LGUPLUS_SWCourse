package com.mycom.springbootjparoleshop.product.dto;

import com.mycom.springbootjparoleshop.orders.entity.Orders;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

	private Long id;
	private String name;
	private int price;
	private int quantity;
	private String country;
	private List<Orders> orders = new ArrayList<>();
	
}
