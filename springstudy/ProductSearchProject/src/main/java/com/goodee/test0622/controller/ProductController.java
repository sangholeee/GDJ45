package com.goodee.test0622.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.test0622.domain.SearchVO;
import com.goodee.test0622.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@GetMapping(value="/product/search", produces="application/json")
	public String productSearch(SearchVO search) {
		return productService.productSearch(search);
	}
	
}
