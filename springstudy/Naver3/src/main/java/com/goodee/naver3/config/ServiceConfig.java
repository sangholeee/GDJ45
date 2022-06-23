package com.goodee.naver3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.naver3.domain.NaverDTO;
import com.goodee.naver3.mapper.NaverMapper;
import com.goodee.naver3.service.NaverService;
import com.goodee.naver3.service.NaverServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public NaverService naverService() {
		return new NaverServiceImpl();
	}
	
}
