package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AddService;
import service.ListService;
import service.QueryService;
import service.StaffService;

@WebServlet("*.json")
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StaffController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		StaffService service = null;
		switch (command) {
		case "add.json":
			service = new AddService();
			break;
		case "list.json":
			service = new ListService();
			break;
		case "query.json":
			service = new QueryService();
			break;
		}
		if (service != null) {
			service.execute(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
