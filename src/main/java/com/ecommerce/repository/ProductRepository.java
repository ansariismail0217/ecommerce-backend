package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByNameContaining(@RequestParam("name") String name);
	
}
