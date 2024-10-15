package com.Student.Estore.configuration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

	 @Bean
	 public ModelMapper modelMapper() {
	     ModelMapper modelMapper = new ModelMapper();
	    return modelMapper;
	 }

}
