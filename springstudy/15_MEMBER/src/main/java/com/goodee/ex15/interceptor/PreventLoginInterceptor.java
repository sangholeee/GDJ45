package com.goodee.ex15.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class PreventLoginInterceptor implements HandlerInterceptor {

	// 로그인을 했는데, 다시 로그인 또는 회원가입을 수행하려고 할 때 못하게 하기
	
	// 상황 발생 시 컨텍스트 패스로 이동시키기
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("loginMember") != null) {
			// response.sendRedirect(request.getContextPath());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당 기능은 사용할 수 없습니다.')");
			out.println("location.href='" + request.getContextPath() + "'");
			out.println("</script>");
			out.close();
			
			return false;    // 컨트롤러 동작 X
		}
		
		return true;         // 컨트롤러 동작 ㄱㄱ
		
	}
	
}
