package com.hs.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AllReviewApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AllReviewApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AllReviewApplication.class); 
	}
}
