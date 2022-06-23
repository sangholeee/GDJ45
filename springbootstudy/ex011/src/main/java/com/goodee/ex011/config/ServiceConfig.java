package com.goodee.ex011.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex011.service.BbsService;
import com.goodee.ex011.service.BbsServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public BbsService bbsService() {
		return new BbsServiceImpl();
	}
	
}
