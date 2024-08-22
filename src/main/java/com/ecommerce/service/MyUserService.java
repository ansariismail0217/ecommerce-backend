package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.MyUserDto;
import com.ecommerce.exceptions.ItemNotFoundException;
import com.ecommerce.model.MyUser;
import com.ecommerce.model.Product;
import com.ecommerce.repository.MyUserRepository;
import com.ecommerce.repository.ProductRepository;

@Service
public class MyUserService {
	
	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
//	CREATE
	public MyUser createUser(MyUser myUser) {
		return userRepository.save(myUser);
	}
	
//	READ
	public MyUser getById(int id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new ItemNotFoundException("No user found with the given description"));
	}
	
//	READ - ALL USERS
	public List<MyUser> getAll(){
		return userRepository.findAll();
	}
	
//	READ
//	Lazy Fetch: this will return categories with id and name fields only
	public List<MyUserDto> getAllLazyFetch() {
		return userRepository.findLazyUsers();
	}
	
//	UPDATE
	public MyUser updateById(int id, MyUser myUser) {
		if(userRepository.existsById(id)) {
			MyUser existingUser = userRepository.findById(id).get();
			existingUser.setName(myUser.getName());
			existingUser.setAddress(myUser.getAddress());
			existingUser.setProducts(myUser.getProducts());
			return userRepository.save(existingUser);
		}
		throw new ItemNotFoundException("No user found with the given description");
	}
	
//	SAVE PRODUCT IN USER CART
	public Product saveInUser(int id, Product product) {
		if (productRepository.existsById(id)) {
			Product product2 = productRepository.findById(id).get();
			product2.setMyUser(product.getMyUser());
			return productRepository.save(product2);
		}
		throw new ItemNotFoundException("No item found with the given description");
	}
	
//	DELETE
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

}
