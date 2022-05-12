package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.StaffDTO;
import repository.StaffDAO;

public class DetailService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String sno = request.getParameter("query");
		
		
		StaffDTO staff = StaffDAO.getInstance().selectStaffByNo(sno);
		
		JSONObject obj = new JSONObject(staff);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
	}

}
