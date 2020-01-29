package com.hs.review.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hs.review.interceptor.JwtAuthInterceptor;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
	private String[] whiteList = {
			"/user/signup/**",
			"/user/login/**",
			"/user/idcheck/**"
	};
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtAuthInterceptor())
				.addPathPatterns("/*")
				.excludePathPatterns(whiteList);
	}
}
