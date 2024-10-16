package com.Student.Estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Student.Estore.models.ProductDetails;

public interface productDetailsRepository extends JpaRepository<ProductDetails,Long>{
	
	//check the product
	boolean existsByproductName(String productName);
}
