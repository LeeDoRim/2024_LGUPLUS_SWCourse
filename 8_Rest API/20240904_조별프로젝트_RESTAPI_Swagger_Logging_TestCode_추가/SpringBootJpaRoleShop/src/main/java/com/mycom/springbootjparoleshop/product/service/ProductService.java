package com.mycom.springbootjparoleshop.product.service;

import com.mycom.springbootjparoleshop.product.dto.ProductResultDto;
import com.mycom.springbootjparoleshop.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	
	// 회원 상품 조회
	ProductResultDto listAllProduct();

	Product insert(Product product);
	Product update(Product product);
	List<Product> listProducts();
	void deleteById(Long id);
	Optional<Product> findById(Long id);

}
