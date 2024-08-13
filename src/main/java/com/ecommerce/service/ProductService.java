package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
//	CREATE
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
//	READ
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public Product getById(Integer id) {
		return productRepository.findById(id).get();
	}
	
	public List<Product> getProductByName(String name) {
		return productRepository.findByNameContaining(name);
	}
	
	public ProductDto findProductById(Integer id) {

		Product product = productRepository.findById(id).get();

		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(product.getCategory().getId());
		categoryDto.setName(product.getCategory().getName());

		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setRating(product.getRating());
		productDto.setPrice(product.getPrice());
		productDto.setCategory(categoryDto);

		return productDto;

	}
	
//	UPDATE
	public List<Product> updateProduct(int id, Product product) {
		if (productRepository.existsById(id)) {

			Product product2 = productRepository.findById(id).get();
			product2.setName(product.getName());
			product2.setDescription(product.getDescription());
			product2.setRating(product.getRating());
			product2.setPrice(product.getPrice());

			productRepository.save(product2);
		}
		return productRepository.findAll();
	}
	
//	DELETE
	public List<Product> deleteProduct(int id) {
		productRepository.deleteById(id);
		return productRepository.findAll();
	}

}
