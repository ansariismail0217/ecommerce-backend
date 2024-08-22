package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ecommerce.dto.ProductsByCategoryDto;
//import com.ecommerce.dto.ProductsByCategoryDto;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
//	CREATE
	@PostMapping("/add")
	public ResponseEntity<List<CategoryDto>> addCategory(@RequestBody Category category){
		categoryService.createCategory(category);
		return new ResponseEntity<>(categoryService.getAllLazyFetch(), HttpStatus.CREATED);
	}
	
//	READ
//	Lazy Fetch: this will return categories with id and name fields only
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getLazy() {
		return new ResponseEntity<>(categoryService.getAllLazyFetch(), HttpStatus.OK);
	}
	
//	Eager Fetch: this will return categories with all fields 
//	including all the products inside them
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAll(){
		return new ResponseEntity<>(categoryService.getAllEagerFetch(), HttpStatus.OK);
	}
	
//	Eager Fetch: Get category by id along with the products stored inside it
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
		return new ResponseEntity<>(categoryService.getByIdEagerFetch(id), HttpStatus.OK);
	}
	
//	get by name with products stored inside it 
	@GetMapping("/search")
	public ResponseEntity<Category> getCategoryByName(@RequestParam("name") String name) {
		return new ResponseEntity<>(categoryService.getByName(name), HttpStatus.OK);
	}
	
//	get only the products stored inside the given category
	@GetMapping("/{id}/details")
	public ResponseEntity<ProductsByCategoryDto> findProducts(@PathVariable Integer id) {		
		return new ResponseEntity<>(categoryService.findProducts(id), HttpStatus.OK);
	}
	
//	UPDATE
	@PutMapping("/{id}/update")
	public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
		return new ResponseEntity<>(categoryService.updateById(id, category), HttpStatus.OK);
	}
	
//	DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<Category>> deleteCategory(@PathVariable int id){
		categoryService.deleteById(id);
		return new ResponseEntity<>(categoryService.getAllEagerFetch(), HttpStatus.OK);
	}

}