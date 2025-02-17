package com.Student.Estore.controllers;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Estore.commonresponse.Response;
import com.Student.Estore.enumeration.responseStatus;
import com.Student.Estore.models.User;
import com.Student.Estore.services.userService;
import io.github.bucket4j.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class userController {
	
	
	@Autowired
	private userService userservice;
	
	 private final Bucket bucket;

	    public userController() {
	        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(2, Duration.ofMinutes(1))); 
	        this.bucket = Bucket.builder().addLimit(limit).build();
	    }

	    private boolean isRateLimited() {
	    	System.out.println(bucket.getAvailableTokens());
	        return !bucket.tryConsume(1); 
	    }
	
	@PostMapping("/createuser")
	public Response createUser(@Valid @RequestBody User user)
	{
		Response apiResponse = new Response();
		try
		{
			apiResponse = userservice.createUser(user);
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while creating an user");
		}
		
		return apiResponse;
	}
	
	
	@GetMapping
	public Response getAllUser()
	{
		if (isRateLimited()) {
            return buildRateLimitResponse();
        }
		
		Response apiResponse = new Response();
		
		try
		{
			apiResponse = userservice.getUser();
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while getting an user");
		}
		
		return apiResponse;
	}
	
	
	@GetMapping("/getuseremail/{email}")
	public Response getUserbyEmail(@PathVariable String email)
	{
		
		Response apiResponse = new Response();
		
		try
		{
			apiResponse = userservice.getUserbyEmail(email);
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while getting an user by email");
		}
		
		return apiResponse;
	}
	
	
	@DeleteMapping("/deleteuser/{email}")
	public Response deleteUser(@PathVariable String email)
	{
		
		Response apiResponse = new Response();
		
		try
		{
			apiResponse = userservice.deleteUser(email);
			
		}
		catch(Exception e)
		{
			apiResponse.setError(e.getMessage());
			apiResponse.setStatus(500);
			apiResponse.setData(e.getMessage());
			apiResponse.setError(responseStatus.FAILED);
			apiResponse.setSuggestion("Please try again later or contact support");
			apiResponse.setDescription("An error occured while deleting an user by email");
		}
		
		return apiResponse;
	}
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	private Response buildRateLimitResponse() {
        Response apiResponse = new Response();
        apiResponse.setStatus(429); 
        apiResponse.setError(responseStatus.FAILED);
        apiResponse.setDescription("Rate limit exceeded");
        apiResponse.setSuggestion("Please wait before making more requests");
        return apiResponse;
    }
}
