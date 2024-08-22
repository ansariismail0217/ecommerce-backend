package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductsByCategoryDto;
import com.ecommerce.exceptions.ItemNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

//	CREATE
	public Category createCategory(Category category){
		return categoryRepository.save(category);
	}

//	READ
//	Lazy Fetch: this will return categories with id and name fields only
	public List<CategoryDto> getAllLazyFetch() {
		return categoryRepository.findLazyCategories();
	}

//	Eager Fetch: this will return categories with all fields 
//	including all the products inside them
	public List<Category> getAllEagerFetch() {
		return categoryRepository.findAll();
	}
	
//	Eager Fetch: Get category by id along with the products stored inside it
	public Category getByIdEagerFetch(Integer id) {
		return categoryRepository.findById(id)
//		return categoryRepository.findProductsByCategory(id)
				.orElseThrow(()-> new ItemNotFoundException("No item found with the given description"));
	}
	
	public Category getByName(String name) {
		if(categoryRepository.existsByName(name)) {
			return categoryRepository.findByName(name);
		}
		throw new ItemNotFoundException("No item found with the given description");
	}
	
//	Get only products stored inside a category
	public ProductsByCategoryDto findProducts(Integer id) {
		Category category =  this.categoryRepository.findById(id).get();
		
		ProductsByCategoryDto productsByCategoryDto = new ProductsByCategoryDto();
		productsByCategoryDto.setProducts(category.getProducts());
		
		return productsByCategoryDto;
	}

//	UPDATE
	public Category updateById(int id, Category category) {
		Category category2 = categoryRepository.findById(id).get();
		category2.setName(category.getName());
		return categoryRepository.save(category2);
	}

//	DELETE
	public List<Category> deleteById(int id){
		categoryRepository.deleteById(id);
		return categoryRepository.findAll();
	}

}
