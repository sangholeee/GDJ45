package com.goodee.naver3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.naver3.service.NaverService;

@Controller
public class NaverController {

	@Autowired
	private NaverService naverService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		model.addAttribute("login", naverService.login(session));
		return "login";
	}
	@GetMapping("callback")
	public String session(HttpServletRequest request) {
		naverService.callback(request);
		return "login";
	}
	
}
