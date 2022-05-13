package com.goodee.ex05.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex05.service.MemberService;

@Controller
public class MemberController {

	// 컨트롤러는 언제나 Service를 호출합니다.
	// 그래서 Service를 field로 등록합니다.
	
	// DI를 사용 안 하는 경우
	// 개발자가 직접 필요한 Bean을 생성하는 방법
	// private MemberService memberservice = new MemberServiceImpl();
	
	// DI를 사용하는 경우
	// root-context.xml에 등록한 Bean을 스프링이 가져오는 방법
	
	// 필드, 생성자, setter 방식 중 필드 주입 방식 사용해 봅니다.
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";     // index.jsp
	}
	
	@GetMapping("/member")
	public String member() {
		return "member";    // member.jsp
	}
	
	
	// 컨트롤러의 메소드는 기본적으로 JSP이름을 반환한다.
	
	// Ajax는 JSP 이름을 반환하는 것이 아니라
	// 자신을 호출한 JSP로 값을 반환하는 구조이다.
	
	// 값을 반환하기 위해서는
	// @ResponseBody 애너테이션이 필요하다.
	
	@GetMapping(value="/member/detail1", 
				produces = "text/plain; charset=UTF-8")  // 내가 반환하는 건 텍스트입니다. (응답 타입 response.setContentType)
	@ResponseBody       // 내가 반환하는 건 JSP 이름이 아니라 어떤 값(텍스트, XML, JSON 등)이에요.
	                    // 있으면 Ajax(페이지 안바뀜) 처리, 없으면 MVC(페이지 바뀜) 처리.
	public String detail1(HttpServletRequest request) {  // 파라미터 id와 pw를 request로 받는다.
		String res = memberService.detail1(request);
		return res;     // memberService의 detail1() 메소드에서 만든 텍스트를 member.jsp로 반환한다.
	}
	
}
