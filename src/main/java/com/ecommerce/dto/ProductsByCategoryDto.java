package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Product;

public class ProductsByCategoryDto {
	
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
