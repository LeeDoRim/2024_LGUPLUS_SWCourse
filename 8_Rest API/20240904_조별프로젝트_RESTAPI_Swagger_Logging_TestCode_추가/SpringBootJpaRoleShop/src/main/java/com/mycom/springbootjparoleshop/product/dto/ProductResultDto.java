package com.mycom.springbootjparoleshop.product.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResultDto {
	
	private String result;
	private ProductDto productDto;
	private List<ProductDto> productList = new ArrayList<>();

}
