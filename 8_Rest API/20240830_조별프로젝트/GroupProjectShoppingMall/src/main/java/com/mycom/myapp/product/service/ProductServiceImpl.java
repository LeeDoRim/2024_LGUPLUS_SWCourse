package com.mycom.myapp.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.myapp.product.dto.ProductDto;
import com.mycom.myapp.product.dto.ProductResultDto;
import com.mycom.myapp.product.entity.Product;
import com.mycom.myapp.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	// 회원 상품 조회
	@Override
	public ProductResultDto listAllProduct() {
		ProductResultDto productResultDto = new ProductResultDto();
		List<ProductDto> productListDto = productResultDto.getProductList();
		
		List<Product> productList = productRepository.findAll();
		
		productList.forEach( product -> {
			
			ProductDto productDto = new  ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setQuantity(product.getQuantity());
			productDto.setCountry(product.getCountry());
			
//			System.out.println(productDto);
			
			productListDto.add(productDto);
		});
		
		productResultDto.setResult("success");
		return productResultDto;
	}

}
