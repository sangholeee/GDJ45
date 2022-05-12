package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex04.domain.Product;

@Controller
public class Controller3 {

	@PostMapping("/remove1")
	public String remove1(HttpServletRequest request, Model model1) {
		
		String model = request.getParameter("model");
		Long price = Long.parseLong(request.getParameter("price"));
		
		Product product = new Product(model, price);
		model1.addAttribute("product", product);
		
		return "product";
	}
	
	@PostMapping("/remove2")
	public String remove2(@RequestParam(value="model", required=false, defaultValue="무엇") String model,
						@RequestParam(value="price", required=false, defaultValue="0") Long price,
						Model model1) {
		
		model1.addAttribute("product", new Product(model, price));
		
		return "product";
		
	}
	
	@PostMapping("/remove3")
	public String remove3(Product product,
						Model model) {
		
		model.addAttribute("product", product);
		
		return "product";
		
	}
	
	
}
