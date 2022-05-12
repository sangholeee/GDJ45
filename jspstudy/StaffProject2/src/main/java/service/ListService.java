package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import domain.Staff;
import repository.StaffDAO;

public class ListService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<Staff> list = StaffDAO.getInstance().selectStaffList();
		
		JSONArray arr = new JSONArray(list);
			
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(arr);
		out.close();

	}

}
