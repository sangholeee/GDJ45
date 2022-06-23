package com.goodee.test0622.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.test0622.service.ProductService;
import com.goodee.test0622.service.ProductServiceImpl;

@Configuration
public class ServiceConfig {
	
	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}
	
}
