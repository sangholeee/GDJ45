package com.goodee.ex15.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.ex15.domain.SignOutMemberDTO;
import com.goodee.ex15.service.MemberService;
import com.goodee.ex15.util.SecurityUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberService memberService;
	
	
	// @PostMapping("/member/login") 요청 이전에 처리
	// 탈퇴자인지 여부 확인
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 반환타입이 true 이면 @PostMapping("/member/login") 요청을 수행한다.
		// 반환타입이 false 이면  @PostMapping("/member/login") 요청을 수행하지 않기 때문에 작업을 직접 해줘야 한다.
		
		// 로그인 된 정보가 있다면 기존 로그인 정보를 제거
		HttpSession session = request.getSession();
		if(session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		
		// 탈퇴한 회원인지 확인
		String id = SecurityUtils.xss(request.getParameter("id"));
		SignOutMemberDTO member = memberService.findSignOutMember(id);
		
		if(member != null) {   // 탈퇴한 회원이면 
			// member 안에 탈퇴한 회원의 정보가 들어있다.
			// 탈퇴한 회원의 정보를 가지고 재가입 페이지로 이동
			request.setAttribute("member", member);
			request.getRequestDispatcher("/member/reSignInPage").forward(request, response);        // Forward는 서버 내부에서 내부로 이동하기 때문에 contextPath 없이 Mapping으로만 이동한다.
			return false;
		} 
		
		return true;
		
		
	}
	
	// @PostMapping("/member/login") 요청 이후에 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//  ModelAndView -> controller가 사용한 model의 데이터를 가져온다.
		
		// ModelAndView를 Map으로 변환하고 loginMember를 추출
		Map<String, Object> map = modelAndView.getModel();
		Object loginMember = map.get("loginMember");
		
		// loginMember가 있다면(로그인 성공) session에 저장
		if(loginMember != null) {
			request.getSession().setAttribute("loginMember", loginMember);
		}
		// loginMember가 없다면 로그인 실패
		else {
			response.sendRedirect(request.getContentType() + "/member/loginPage");                                                  // Redirect 는 외부에서 접근하는 것이기 때문에 contextPath가 필요하다.
		}
		
		
	}
}
