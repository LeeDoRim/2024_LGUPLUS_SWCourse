package com.mycom.springbootjparoleshop.product.service;

import com.mycom.springbootjparoleshop.product.dto.ProductDto;
import com.mycom.springbootjparoleshop.product.dto.ProductResultDto;
import com.mycom.springbootjparoleshop.product.entity.Product;
import com.mycom.springbootjparoleshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Product insert(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Product> findById(Long id) { return productRepository.findById(id); }

}
