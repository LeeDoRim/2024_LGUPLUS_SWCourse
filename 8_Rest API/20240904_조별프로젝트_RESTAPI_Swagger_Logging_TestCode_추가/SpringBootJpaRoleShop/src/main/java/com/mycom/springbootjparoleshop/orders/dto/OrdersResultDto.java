package com.mycom.springbootjparoleshop.orders.dto;

import com.mycom.springbootjparoleshop.product.dto.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrdersResultDto {
	private String result;
	private ProductDto productDto;
	private List<ProductDto> productList = new ArrayList<>();
	private List<OrdersDto> listOrders;
}
