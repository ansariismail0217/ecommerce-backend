package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductsByCategoryDto;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

//	CREATE
	public Category addCategory(final Category category){
		return categoryRepository.save(category);
	}

//	READ
//	Lazy Fetch: this will return categories with id and name fields only
	public List<CategoryDto> getLazy() {
		return categoryRepository.findLazyCategories();
	}

//	Eager Fetch: this will return categories with all fields 
//	including all the products inside them
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	public Category getCategory(int id) {
		return categoryRepository.findById(id).get();
	}
	
//	Eager Fetch: Get category by id along with the products stored inside it
	public Category getById(Integer id) {
		return categoryRepository.findProductsByCategory(id);
	}
	
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
	
//	Get only products stored inside a category
	public ProductsByCategoryDto findProducts(Integer id) {
		Category category =  this.categoryRepository.findById(id).get();
		
		ProductsByCategoryDto productsByCategoryDto = new ProductsByCategoryDto();
		productsByCategoryDto.setProducts(category.getProducts());
		
		return productsByCategoryDto;
	}

//	UPDATE

//	DELETE
	public List<Category> deleteCategory(int id){
		categoryRepository.deleteById(id);
		return categoryRepository.findAll();
	}

}
