package com.goodee.ex05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex05.service.OpenapiService;

@Controller
public class OpenapiController {

	// Map으로 해서 안되면 그냥 String ㄱㄱ
	
	@Autowired
	private OpenapiService openapiService;
	
	@ResponseBody
	@GetMapping(value="/openapi/dailyBoxOffice",
				produces="application/json; charset=UTF-8")
	public String dailyBoxOffice(@RequestParam String targetDt) {
		return openapiService.dailyBoxOffice(targetDt);
	}
}
