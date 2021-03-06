package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class ModifyService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		Optional<String> optFreeNo = Optional.ofNullable(request.getParameter("freeNo"));
		Long freeNo = Long.parseLong(optFreeNo.orElse("0"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Free board = new Free();
		board.setFreeNo(freeNo);
		board.setTitle(title);
		board.setContent(content);
		
		int res = FreeDAO.getInstance().updateBoard(board);
		
		
		try {
			if(res > 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 수정되었습니다.')");
				out.println("location.href='/BoardProject/detail.do?freeNo=" + freeNo + "'");
				out.println("</script>");
				out.flush();
				out.close();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;


	}

}
