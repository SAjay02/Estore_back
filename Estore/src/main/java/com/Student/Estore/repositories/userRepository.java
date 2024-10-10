package com.Student.Estore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Student.Estore.models.User;

public interface userRepository extends JpaRepository<User,Long>{

	//get user by name` and update
	List<User> findByEmail(String email);
	
	//delete items by email
	void deleteByEmail(String email);
}
