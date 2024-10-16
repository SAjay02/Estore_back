package com.Student.Estore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Student.Estore.commonresponse.Response;
import com.Student.Estore.enumeration.responseStatus;
import com.Student.Estore.models.ProductDetails;
import com.Student.Estore.models.Products;
import com.Student.Estore.repositories.productDetailsRepository;

@Service
public class productDetailsServices {
	
	@Autowired
	private ModelMapper modelmap;
	
	@Autowired
	private productDetailsRepository productdetailsrepo;
	
	//add the products
	public Response createProduct(ProductDetails productdetail)
	{
		Response apiResponse = new Response();
		
		boolean existProduct = productdetailsrepo.existsByproductName(productdetail.getProductName());
		
		
		if(!existProduct)
		{
			ProductDetails newProduct = modelmap.map(productdetail,ProductDetails.class);
			productdetailsrepo.save(newProduct);
			
			apiResponse.setData(newProduct);
			apiResponse.setDescription("Product Created Successfully");
			apiResponse.setStatus(201);
		}
		else
		{
			apiResponse.setDescription("Product has already Created Successfully");
			apiResponse.setStatus(404);
			apiResponse.setSuggestion("Please Create a new product with unique name");
			apiResponse.setError(responseStatus.FAILED);
		}
		
		return apiResponse;
		
	}
	
}
