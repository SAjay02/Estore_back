package com.Student.Estore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "products")
//@Data
public class Products {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Name cannot be empty")
	@NotBlank(message = "Name cannot be empty")
	private String productName;
	
	@NotNull(message = "Cost cannot be empty")
	@NotBlank(message = "Cost cannot be empty")
	private String productCost;
	
	@NotNull(message = "Description cannot be empty")
	@NotBlank(message = "Description cannot be empty")
	private String productDescription;
	

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductCost() {
		return productCost;
	}


	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
