package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.FreeDAO;

public class RemoveService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Optional<String> optFreeNo = Optional.ofNullable(request.getParameter("freeNo"));
		Long freeNo = Long.parseLong(optFreeNo.orElse("0"));
		
		int res = FreeDAO.getInstance().deleteBoard(freeNo);
		
		ActionForward af = null;
		if(res > 0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글이 삭제되었습니다.')");
			out.println("location.href='/BoardProject/list.do'");
			out.println("</script>");
			out.flush();
			out.close();
		} 		
		return af;
	}

}
