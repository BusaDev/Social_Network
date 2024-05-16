package com.cina.reput;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReputApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReputApplication.class, args);
	}


	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}
}
