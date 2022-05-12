package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.JSONObject;

import domain.Staff;
import repository.StaffDAO;

public class AddService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		
		try {
			
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			Integer salary = 0;
			switch(dept) {
			case "기획부": salary = 5000; break;
			case "개발부": salary = 6000; break;
			case "영업부": salary = 7000; break;
			default: salary = 4000;
			}
			
			Staff staff = new Staff();
			staff.setSno(sno);
			staff.setName(name);
			staff.setDept(dept);
			staff.setSalary(salary);
			
			int res = StaffDAO.getInstance().insertStaff(staff);
			
			JSONObject obj = new JSONObject();
			obj.put("res", res);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.close();
			
		} catch (PersistenceException e) {
			
			response.setContentType("text/plain; charset=UTF-8");
			response.setStatus(1001);
			response.getWriter().println("저장할 수 없는 값이 전달되었습니다.");
			
		}

	}

}
