package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDAO;


public class ListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 응답 데이터 형식
		// JSON
		response.setContentType("application/json; charset=UTF-8");

		
		// 응답 데이터 예시
		// {
		// 	  "count": 6,           -- obj.put(key, value)
		//	  "members": [          -- obj.put("count", 7)
		//		{
		//			"no": 1,
		//			"id": "user1",
		//			"name": "사용자1",
		//			"gender": "female",
		//			"address": "seoul"
		//		},
		//		...
		// 	]
		// }
		// 중괄호로 열리는건 JSONObject
		// 대괄호로 열리는건 JSONArray
		
		 JSONObject obj = new JSONObject();
		 obj.put("count", MemberDAO.getInstance().getTotalCount());
		 obj.put("members", MemberDAO.getInstance().selectMemberList());
		
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
	}

}
