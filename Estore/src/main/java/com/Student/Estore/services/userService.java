package com.Student.Estore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Student.Estore.commonresponse.Response;
import com.Student.Estore.models.User;
import com.Student.Estore.repositories.userRepository;
import com.Student.Estore.enumeration.responseStatus;

@Service
public class userService {
	
	@Autowired
	private userRepository userRepo;
	
	
	//create the user endpoint
	public User createUser(User user)
	{
		return userRepo.save(user);
	}
	
	//get all user endpoint
	public Response getUser()
	{
		List<User> existUser = userRepo.findAll();
		
		Response apiResponse = new Response();
		
		if(existUser!=null || !existUser.isEmpty())
		{
			apiResponse.setData(existUser);
			apiResponse.setStatus(200);
			apiResponse.setDescription("All User Retrieved Successfully");
		}
		else
		{
			apiResponse.setStatus(404);
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setDescription("User Not Found");
			apiResponse.setSuggestion("Please check the user has created");
		}
		
		return apiResponse;
	}
	
	//get the user by email endpoint
	public Response getUserbyEmail(String email)
	{
		List<User> existUser = userRepo.findByEmail(email);
		
		Response apiResponse = new Response();
		
		if(!existUser.isEmpty())
		{
			apiResponse.setData(existUser);
			apiResponse.setStatus(200);
			apiResponse.setDescription("User Retrieved Successfully bu Email");
		}
		else
		{
			apiResponse.setStatus(404);
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setDescription("User Not Found");
			apiResponse.setSuggestion("Please check the user has created");
		}
		return apiResponse;
	}
	
	
	//delete the user by email endpoint
	@Transactional
	public Response deleteUser(String email)
	{
		
		Response apiResponse = new Response();
		
		List<User> existUser = userRepo.findByEmail(email);
		
		if(!existUser.isEmpty())
		{
			User delUser = existUser.get(0);
			
			userRepo.deleteByEmail(delUser.getEmail());
			
			System.out.print(delUser);
			
			apiResponse.setData(delUser);
			apiResponse.setStatus(200);
			apiResponse.setDescription("User Deleted Successfully by Email");
		}
		else
		{
			apiResponse.setStatus(404);
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setDescription("User Not Found");
			apiResponse.setSuggestion("Please check the user has created");
		}
		
		return apiResponse;

	}
}
