package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.exceptions.ItemNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
//	CREATE
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
//	READ
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
//	this will fetch details lazily excluding category fields
	public Product getByIdLazyFetch(Integer id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new ItemNotFoundException("No item found with the given description"));
	}
	
//	this will fetch details eagerly including category field using dtos
	public ProductDto getByIdEagerFetch(Integer id) {

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
	
	public Product getByName(String name) {
		if(productRepository.existsByName(name)) {
			return productRepository.findByName(name);
		}
		throw new ItemNotFoundException("No item found with the given description");
	}
	
//	UPDATE
	public Product updateById(int id, Product product) {
		if (productRepository.existsById(id)) {

			Product product2 = productRepository.findById(id).get();
			product2.setName(product.getName());
			product2.setDescription(product.getDescription());
			product2.setRating(product.getRating());
			product2.setPrice(product.getPrice());

			return productRepository.save(product2);
		}
		
		throw new ItemNotFoundException("No item found with the given description");
	}
	
//	DELETE
	public List<Product> deleteById(int id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return productRepository.findAll();
		}
		
		throw new ItemNotFoundException("No item found with the given description");
		
	}

}
