package com.goodee.kakao2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.kakao2.service.KakaoService;
import com.goodee.kakao2.service.KakaoServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public KakaoService kakaoService() {
		return new KakaoServiceImpl();
	}
	
}
