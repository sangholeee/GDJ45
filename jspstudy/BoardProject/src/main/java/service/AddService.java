package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class AddService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		Free board = new Free();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setIp(ip);
		
		int res = FreeDAO.getInstance().insertBoard(board);
		
		ActionForward af = null;
		if(res > 0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다.')");
			out.println("location.href='/BoardProject/list.do'");
			out.println("</script>");
			out.flush();
			out.close();
		} 
		
		return af;
	}

}