package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<CategoryDto> addCategory(@RequestBody Category category){
		categoryService.addCategory(category);
		return categoryService.getLazy();
	}
	
//	READ
//	Lazy Fetch: this will return categories with id and name fields only
	@GetMapping("/all")
	public List<CategoryDto> getLazy() {
		return categoryService.getLazy();
	}
	
//	Eager Fetch: this will return categories with all fields 
//	including all the products inside them
	@GetMapping("/all/details")
	public List<Category> getAll(){
		return categoryService.getAll();
	}
	
//	Eager Fetch: Get category by id along with the products stored inside it
//	request param uses query params as key and value in postman
//	@GetMapping("/get")
//	public Category getCategory(@RequestParam("id") int id) {
//		return categoryService.getCategory(id);
//	}
	
//	Eager Fetch: Get category by id along with the products stored inside it
//	get same result as above but id passed directly with url instead of query param
	@GetMapping("/{id}")
	public Category getById(@PathVariable Integer id) {
		return categoryService.getById(id);
	}
	
//	get by name
	@GetMapping("/get")
	public Category getCategoryByName(@RequestParam("name") String name) {
		return categoryService.getCategoryByName(name);
	}
	
	@GetMapping("/{id}/details")
	public ProductsByCategoryDto findProducts(@PathVariable Integer id) {		
		return categoryService.findProducts(id);
	}
	
//	UPDATE
	
//	DELETE
	@DeleteMapping("/delete/{id}")
	public List<Category> deleteCategory(@PathVariable int id){
		categoryService.deleteCategory(id);
		return categoryService.getAll();
	}

}