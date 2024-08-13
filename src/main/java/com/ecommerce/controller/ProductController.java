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
import com.ecommerce.service.ProductService;

//http://localhost:8080/products

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	CREATE
	@PostMapping(value = "/add")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
//	READ
//	find all products
	@GetMapping("/all")
	public List<Product> getAll() {
		return productService.getAll();
	}
	
//	get product by id lazy fetch
	@GetMapping("/{id}")
	public Product getById(@PathVariable Integer id) {
		return productService.getById(id);
	}
	
//	get by name
	@GetMapping("/search")
	public List<Product> getProductByName(@RequestParam("name") String name) {
		return productService.getProductByName(name);
	}
	
//	get product details eager fetch using dto
	@GetMapping("/{id}/details")
	public ProductDto findProductById(@PathVariable Integer id) {

		Product product = productService.getById(id);

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
	@PutMapping(value = "/update/{id}")
	public List<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	
//	DELETE
	@DeleteMapping(value = "/delete/{id}")
	public List<Product> deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return productService.getAll();
	}

}
