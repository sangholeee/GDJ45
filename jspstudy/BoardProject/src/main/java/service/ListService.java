package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import repository.FreeDAO;

public class ListService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		HttpSession session = request.getSession();
		if(session.getAttribute("board") != null) {
			session.removeAttribute("board");
		}
		if(session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		request.setAttribute("list", FreeDAO.getInstance().selectBoardList());
		return new ActionForward("free/list.jsp", false);
	}

}
		