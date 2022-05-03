package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AddService;
import service.DetailService;
import service.ListService;
import service.MemberService;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html; charset=UTF-8");    각 서비스에서 처리하고 있다.
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		MemberService service = null;
		
		ActionForward af = null;
		
		switch(command) {
		case "memberManage.do" :
			af = new ActionForward("member/manage.jsp", false);
			break;
		case "add.do" :
			service = new AddService();
			break;
		case "list.do" :   // http://localhost:9090/AJAX/list.do -- postman 웹 사이트 이용하면 보기좋게 확인 가능
			service = new ListService();
			break;
		case "detail.do" :
			service = new DetailService();
			break;
		}
		
		if(service != null) {
			service.execute(request, response);
		}
		
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
