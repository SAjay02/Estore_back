package com.Student.Estore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Estore.commonresponse.Response;
import com.Student.Estore.enumeration.responseStatus;
import com.Student.Estore.services.productServices;

@RestController
@RequestMapping("/products")
public class productController {
	
	
	@Autowired
	private productServices productService;
	
	
	@PostMapping("/addproduct/{id}/{userid}")
	public Response addUserProduct(@PathVariable Long id, @PathVariable Long userid)
	{
		Response apiResponse = new Response();
		
		try
		{
			apiResponse = productService.addProductbyUser(id, userid);
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while creating an user along with their products");
		}
		
		return apiResponse;
	}
	
	@GetMapping("/getuserProducts/{userid}")
	public Response getuserProducts(@PathVariable Long userid)
	{
		Response apiResponse = new Response();
		
		try
		{
			apiResponse = productService.getProductbyUser(userid);
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while getting an user along with their products");
		}
		
		return apiResponse;
	}
}
