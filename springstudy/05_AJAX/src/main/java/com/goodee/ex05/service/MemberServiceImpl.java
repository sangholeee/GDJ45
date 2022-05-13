package com.goodee.ex05.service;

import javax.servlet.http.HttpServletRequest;

public class MemberServiceImpl implements MemberService {

	@Override
	public String detail1(HttpServletRequest request) {          // 컨트롤러에서 받아온 request에는 파라미터 id와 pw가 들어 있다.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		return "입력 아이디: " + id + " 입력 비밀번호: " + pw;   // 컨트롤러에 반환할 텍스트
	}

}
