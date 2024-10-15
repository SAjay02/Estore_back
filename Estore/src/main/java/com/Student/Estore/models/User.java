	package com.Student.Estore.models;

import javax.validation.constraints.Min;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name is not Valid")
	@NotNull(message = "Name is mandatory")
	private String name;
	
	@NotNull(message = "email is mandatory")
	@Email(message = "Email Should be Valid")
	@NotEmpty(message = "Email is not Valid")
	private String email;
	
	@NotEmpty(message = "PhoneNumber is not Valid")
	@NotNull(message = "PhoneNumber is mandatory")
//	@Max(10)
//	@Min(10)
	private String phonenumber;
	
	@NotEmpty(message = "Password is not Valid")
	@NotNull(message = "Pasword is mandatory")
	@Size(min=6, max=15, message="Password must be between 6 and 15")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String name, String email, String phonenumber, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
	}
	public User() {
		super();
	}
	
}
