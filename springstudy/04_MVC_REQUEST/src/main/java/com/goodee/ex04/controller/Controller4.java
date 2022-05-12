package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controller4 {

	// redirect 하는 방법
	
	// redirect는 JSP로 이동하지 않습니다.
	// redirect는 다른 매핑으로 이동합니다.
	
	// redirect:매핑
	
	// 꼭. 알. 고. 있. 기.
	
	// forward는 JSP로 이동합니다.
	// redirect는 매핑으로 이동합니다.
	
	// JSP는 '/' 붙이지 말자
	// 매핑은 '/'로 시작하자!

	// 헷갈리면 다 붙여버리기
	
	@GetMapping("/list1")
	public String list1(HttpServletRequest request) {
		
		// forward
		// return "list";   list.jsp로 forward 이동하기
		
		// redirect는 request 전달해 주지 않아요.
		// request나 model에 저장해 봤자 최종 목적지에 전달되지 않아요.
		request.setAttribute("page", request.getParameter("page"));
		
		// redirect
		return "redirect:/list2";     // @GetMapping("/list2") 매핑으로 이동하라는 의미입니다.
		
	}
	
	@GetMapping("/list2")
	public String list2() {
		return "list";     // list.jsp로 forward 합니다.
	}
	
	
	// Request : 손이 많이가지만 에러는 나지 않는다.
	//           Spring 버전을 타지 않는다.
	@GetMapping("/list3")
	public String list3(HttpServletRequest request) {
		
		String page = request.getParameter("page");
		
		// redirect 할 때 데이터를 전달하려면 파라미터를 붙여줘야 합니다.
		return "redirect:/list4?page=" + page;    
		
	}
	
	
	@GetMapping("/list4")
	public String list4(HttpServletRequest request, Model model) {
		
		String page = request.getParameter("page");
		
		model.addAttribute("page", page);
		
		return "list";    
	}
	
	/* 이렇게 하면 줄여서 쓰기 ㄱㄴㄱㄴ
	   Spring 버전에 따라 약간의 에러가 있을 수 있음
	   
	@GetMapping("/list3")
	public String list3(int page) {
		
		return "redirect:/list4?page=" + page;    
		
	}
	
	@GetMapping("/list4")
	public String list4(int page, Model model) {
		
		model.addAttribute("page", page);
		
		return "list";    
	}
	*/
	
	
	// redirect 할 때 데이터 전달하는 스프링의 방법
	
	// 1. Model 대신 RedirectAttributes 인터페이스를 사용한다.
	// 2. addAttribute 대신 addFlashAttribute() 메소드를 사용한다.
	
	@GetMapping("/list5")
	public String list5(int page, 
						RedirectAttributes redirectAttributes) {    // redirect 할 곳으로 데이터를 전송해 줄 수 있습니다.
		
		// addAttribute()를 쓰면 redirect 할 때 전달이 안됩니다.
		// 꼭 addFlashAttribute()를 써야 합니다.
		redirectAttributes.addFlashAttribute("page", page);         // 그냥 addAttribute() 아님!!! 그러면 Model과 다를게 없음. Flash 꼭 들어가야함!
		
		return "redirect:/list6";   // page 파라미터를 안 붙여도 전달됩니다.
		
	}
	
	@GetMapping("/list6")
	public String list6() {
		return "list";    // list.jsp로 forward 합니다.
	}
	
}
