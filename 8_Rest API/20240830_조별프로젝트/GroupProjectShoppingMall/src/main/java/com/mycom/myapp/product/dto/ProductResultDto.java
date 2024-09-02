package com.mycom.myapp.product.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductResultDto {
	
	private String result;
	private ProductDto productDto;
	private List<ProductDto> productList = new ArrayList<>();

}
