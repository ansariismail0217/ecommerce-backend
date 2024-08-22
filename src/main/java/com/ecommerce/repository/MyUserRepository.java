package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.dto.MyUserDto;
import com.ecommerce.model.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer>{
	
	@Query("select new com.ecommerce.dto.MyUserDto(u.id, u.name, u.address) from MyUser u")
	List<MyUserDto> findLazyUsers();

}
