package com.Student.Estore.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Student.Estore.models.ProductDetails;

public interface productDetailsRepository extends JpaRepository<ProductDetails,Long>{
	
	//check the product
	boolean existsByproductName(String productName);
	
	boolean existsById(Long id);
	
	void deleteById(Long id);
	
}
