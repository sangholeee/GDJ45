package com.goodee.kakao2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.kakao2.service.KakaoService;

@Controller
public class KakaoController {

	@Autowired
	private KakaoService kakaoService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		kakaoService.login();
		System.out.println(kakaoService.login());
		return "success";
	}
	
}
