package com.ecommerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "rating")
	private float rating;

	@Column(name = "price")
	private double price;

//	this Product entity is the owning side of the relation b/w product and customer
	@JsonBackReference("category-product")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToMany
	@JoinTable(name = "user-product", 
	joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "my_user_id"))
//	@JoinColumn(name = "my_user_id")
	private List<MyUser> myUsers;

	public Product() {
	}

	public Product(String name, String description, float rating, double price, Category category,
			List<MyUser> myUsers) {
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.price = price;
		this.category = category;
		this.myUsers = myUsers;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<MyUser> getMyUser() {
		return myUsers;
	}

	public void setMyUser(List<MyUser> myUsers) {
		this.myUsers = myUsers;
	}

}
