package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c left join fetch c.products where c.id =:id")
	Optional<Category> findProductsByCategory(@Param("id") Integer id);
	
	@Query("select new com.ecommerce.dto.CategoryDto(c.id, c.name) from Category c")
	List<CategoryDto> findLazyCategories();
	
	Category findByName(String name);
	
	boolean existsByName(String name);

}
