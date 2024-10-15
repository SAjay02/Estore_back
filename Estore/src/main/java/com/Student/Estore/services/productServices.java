package com.Student.Estore.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Student.Estore.commonresponse.Response;
import com.Student.Estore.enumeration.responseStatus;
import com.Student.Estore.models.Products;
import com.Student.Estore.models.User;
import com.Student.Estore.repositories.productRepository;
import com.Student.Estore.repositories.userRepository;

@Service
public class productServices {

	@Autowired
	private productRepository productRepo;
	
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private ModelMapper modelmap;
	
	//get the products by userid
	public Response getProductbyUser(Long userid)
	{
		Response apiResponse = new Response();
		
		User user = userRepo.findById(userid).
				orElseThrow(() -> new ObjectNotFoundException("Data not found", new User()));
		
		
		List<Products> product = productRepo.findByUser(user);
		
		System.out.print(product);
		
		if(product!=null && !product.isEmpty())
		{
			apiResponse.setData(product);
			apiResponse.setStatus(200);
			apiResponse.setError("User's Product Retrieved Successfully");
		}
		else
		{
			apiResponse.setStatus(404);
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setDescription("User doesn't have any products");
			apiResponse.setSuggestion("Please add the products");
			apiResponse.setData(userid);
		}
		return apiResponse;
	}
}
