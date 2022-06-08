package com.goodee.ex15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex15.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member/agreePage")
	public String agreePage() {
		return "member/agree";
	}

	@GetMapping("/member/signInPage")
	public String signInPage(@RequestParam String[] agreements, Model model) {
		model.addAttribute("agreements", agreements);
		return "member/signIn";
	}
	
	@ResponseBody
	@GetMapping(value="/member/idCheck", produces="application/json")
	public Map<String, Object> idCheck(@RequestParam String id) {
		return memberService.idCheck(id);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/member/emailCheck", produces="application/json")
	public Map<String, Object> emailcheck(@RequestParam String email) {
		return memberService.emailCheck(email);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/member/sendAuthCode", produces="application/json")
	public Map<String, Object> sendAuthCode(@RequestParam String email){
		return memberService.sendAuthCode(email);
	}
	
}
