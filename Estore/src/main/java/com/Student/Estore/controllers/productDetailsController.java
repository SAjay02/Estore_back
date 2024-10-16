package com.Student.Estore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Student.Estore.commonresponse.Response;
import com.Student.Estore.enumeration.responseStatus;
import com.Student.Estore.models.ProductDetails;
import com.Student.Estore.services.productDetailsServices;


@RestController
@RequestMapping("/productsdetails")
public class productDetailsController {
	
	
	@Autowired
	private productDetailsServices productdetailsservice;
	
	@PostMapping("/createproduct")
	public Response addproduct(@RequestBody ProductDetails productdetail)
	{
		Response apiResponse = new Response();
		
		try
		{
			apiResponse = productdetailsservice.createProduct(productdetail);
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while creating an product");
		}
		
		return apiResponse;
	}
	
}
