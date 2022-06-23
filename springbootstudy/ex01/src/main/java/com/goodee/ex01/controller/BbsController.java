package com.goodee.ex01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex01.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	@GetMapping("/")
	public String index() {
		return "index";      //    /WEB-INF/index.jsp
	}
	
	@GetMapping("/bbs/addPage")
	public String addPage() {
		return "bbs/add";    //    /WEB-INF/bbs/add.jsp
	}
	
}
