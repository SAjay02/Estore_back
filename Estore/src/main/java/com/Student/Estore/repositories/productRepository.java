package com.Student.Estore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Student.Estore.models.Products;
import com.Student.Estore.models.User;

public interface productRepository extends JpaRepository<Products,Long>{
	
	//get the products by user
	List<Products> findByUser(User user);
	
	//check the product
//	boolean existsByproductName(String productName);
}
