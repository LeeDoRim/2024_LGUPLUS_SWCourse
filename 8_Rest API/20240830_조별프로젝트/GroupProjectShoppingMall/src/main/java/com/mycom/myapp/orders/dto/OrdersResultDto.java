package com.mycom.myapp.orders.dto;

import java.util.ArrayList;
import java.util.List;

import com.mycom.myapp.product.dto.ProductDto;

import lombok.Data;

@Data
public class OrdersResultDto {
	private String result;
	private ProductDto productDto;
	private List<ProductDto> productList = new ArrayList<>();
}
