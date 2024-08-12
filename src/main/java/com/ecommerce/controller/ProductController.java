package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

//http://localhost:8080/products

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

//	find all products
	@GetMapping("/all")
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
//	get product by id lazy fetch
	@GetMapping("/{id}")
	public Product getById(@PathVariable Integer id) {
		return productRepository.findById(id).get();
	}
	
//	get by name
	@GetMapping("/search")
	public List<Product> getProductByName(@RequestParam("name") String name) {
		return productRepository.findByNameContaining(name);
	}
	
//	get product details eager fetch using dto
	@GetMapping("/{id}/details")
	public ProductDto findProductById(@PathVariable Integer id) {

		Product product = this.productRepository.findById(id).get();

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

//  adding a product
	@PostMapping(value = "/add")
	public List<Product> addProduct(@RequestBody final Product product) {
		productRepository.save(product);
		return productRepository.findAll();
	}

//	deleting a product
	@DeleteMapping(value = "/delete/{id}")
	public List<Product> deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
		return productRepository.findAll();
	}

//	updating a product
	@PutMapping(value = "/update/{id}")
	public List<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
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

}
