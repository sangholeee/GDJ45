package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Staff;
import repository.StaffDAO;

public class QueryService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String query = request.getParameter("query");
		Staff staff = StaffDAO.getInstance().selectStaffBySno(query);
		if(staff != null) {
			JSONObject obj = new JSONObject(staff);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.close();
		}
	}

}
