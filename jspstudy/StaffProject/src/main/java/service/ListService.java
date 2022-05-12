package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.StaffDTO;
import repository.StaffDAO;

public class ListService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<StaffDTO> staff = StaffDAO.getInstance().selectStaffList();
		
		JSONObject obj = new JSONObject();
		obj.put("staff", staff);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
	}
}
