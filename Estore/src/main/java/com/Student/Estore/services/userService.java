package com.Student.Estore.services;
import java.util.regex.*;
import java.util.List;
import org.modelmapper.ModelMapper;
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
	
	
	@Autowired
	private ModelMapper modelmap;
	
	
	//create the user endpoint
	public Response createUser(User user)
	{
		
		boolean existUserByNumber = userRepo.existsByPhonenumber(user.getPhonenumber());
		boolean existUserByEmail = userRepo.existsByEmail(user.getEmail());
		
		Response apiResponse = new Response();
		
		if(!existUserByNumber && !existUserByEmail)
		{

			User newUser = modelmap.map(user,User.class);
			userRepo.save(newUser);
			apiResponse.setStatus(201);
			apiResponse.setDescription("User has Created Successfully");
			apiResponse.setData(newUser);
		}
		else if(existUserByEmail)
		{
			apiResponse.setStatus(403);
			apiResponse.setDescription("User has already found");
			apiResponse.setSuggestion("Please create the user with new email");
			apiResponse.setError(responseStatus.FAILED);
		}
		else
		{
			apiResponse.setStatus(403);
			apiResponse.setDescription("User has already found");
			apiResponse.setSuggestion("Please create the user with new number");
			apiResponse.setError(responseStatus.FAILED);
		}
		return apiResponse;
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
			apiResponse.setDescription("User Retrieved Successfully by Email");
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
