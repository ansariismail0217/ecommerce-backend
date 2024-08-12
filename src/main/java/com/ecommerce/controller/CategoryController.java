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
import com.ecommerce.repository.CategoryRepository;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
//	get all categories lazy fetch
	@GetMapping("/all")
	public List<CategoryDto> getLazy() {
		return categoryRepository.findLazyCategories();
	}
	
//	get all categories eager fetch
	@GetMapping("/all/details")
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
//	get category by id eager fetch products
//	request param uses query params as key and value in postman
//	@GetMapping("/get")
//	public Category getCategory(@RequestParam("id") int id) {
//		return categoryRepository.findById(id).get();
//	}
	
	
//	get category by id eager fetch products
//	get same result as above but id passed directly with url instead of query param
	@GetMapping("/{id}")
	public Category getById(@PathVariable Integer id) {
		return categoryRepository.findProductsByCategory(id);
	}
	
//	get by name
	@GetMapping("/get")
	public Category getCategoryByName(@RequestParam("name") String name) {
		return categoryRepository.findByName(name);
	}
	
	@GetMapping("/{id}/details")
	public ProductsByCategoryDto findProducts(@PathVariable Integer id) {
		Category category =  this.categoryRepository.findById(id).get();
		
		ProductsByCategoryDto productsByCategoryDto = new ProductsByCategoryDto();
		productsByCategoryDto.setProducts(category.getProducts());
		
		return productsByCategoryDto;
	}
	
//	add a new category
	@PostMapping("/add")
	public List<Category> addCategory(@RequestBody final Category category){
		categoryRepository.save(category);
		return categoryRepository.findAll();
	}
	
//	Delete if category has no products
	@DeleteMapping("/delete/{id}")
	public List<Category> deleteCategory(@PathVariable int id){
		categoryRepository.deleteById(id);
		return categoryRepository.findAll();
	}

}