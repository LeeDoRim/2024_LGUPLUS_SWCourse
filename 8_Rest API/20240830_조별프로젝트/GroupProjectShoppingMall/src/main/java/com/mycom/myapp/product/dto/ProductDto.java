package com.mycom.myapp.product.dto;

import java.util.ArrayList;
import java.util.List;

import com.mycom.myapp.orders.entity.Orders;

import lombok.Data;
import lombok.NoArgsConstructor;

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
