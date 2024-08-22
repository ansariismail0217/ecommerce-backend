package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

//http://localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	CREATE
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
	}
	
//	READ
//	find all products
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}
	
//	get product by id lazy fetch
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Integer id) {
		return new ResponseEntity<>(productService.getByIdLazyFetch(id), HttpStatus.OK);
	}
	
//	get product details eager fetch using dto
//	this will include category also in product details
	@GetMapping("/{id}/details")
	public ResponseEntity<ProductDto> findProductById(@PathVariable Integer id) {
		return new ResponseEntity<>(productService.getByIdEagerFetch(id), HttpStatus.OK);

	}
	
//	get by name lazy fetch
	@GetMapping("/search")
	public ResponseEntity<Product> getProductByName(@RequestParam("name") String name) {
		return new ResponseEntity<>(productService.getByName(name), HttpStatus.OK);
	}
	
//	UPDATE
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		return new ResponseEntity<>(productService.updateById(id, product), HttpStatus.OK);
	}
	
//	DELETE
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		return new ResponseEntity<>(productService.deleteById(id), HttpStatus.NO_CONTENT);
	}

}
