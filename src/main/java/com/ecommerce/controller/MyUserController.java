package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.MyUserDto;
import com.ecommerce.model.MyUser;
import com.ecommerce.model.Product;
import com.ecommerce.service.MyUserService;

@RestController
@RequestMapping("/users")
public class MyUserController {
	
	@Autowired
	private MyUserService userService;
	
//	CREATE
	@PostMapping("/add")
	public MyUser addUser(@RequestBody MyUser myUser) {
		return userService.createUser(myUser);
	}
	
//	READ
	@GetMapping("/{id}")
	public MyUser getUserById(@PathVariable int id) {
		return userService.getById(id);
	}
	
//	READ ALL - LAZY
//	This will return list of users without products field
	@GetMapping
	public List<MyUserDto> getAllUsersLazyFetch(){
		return userService.getAllLazyFetch();
	}
	
//	READ ALL
//	This will return list of users with products field
	@GetMapping("/")
	public List<MyUser> getAllUsers(){
		return userService.getAll();
	}
	
//	UPDATE
	@PutMapping("update/{id}")
	public MyUser updateUserById(@PathVariable int id, @RequestBody MyUser myUser) {
		return userService.updateById(id, myUser);
	}
	
//	SAVE PRODUCT IN USER CART
	@PutMapping("{id}/cart")
	public Product saveInUser(@PathVariable int id,@RequestBody Product product) {
		return userService.saveInUser(id, product);
	}
	
//	DELETE
	@DeleteMapping("delete/{id}")
	public void deleteUserById(@PathVariable int id) {
		userService.deleteById(id);
	}

}
