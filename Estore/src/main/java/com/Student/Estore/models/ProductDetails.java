package com.Student.Estore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productDetails")

public class ProductDetails {
	
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


}
