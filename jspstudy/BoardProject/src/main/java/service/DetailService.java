package service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class DetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Optional<String> optFreeNo = Optional.ofNullable(request.getParameter("freeNo"));
		Long freeNo = Long.parseLong(optFreeNo.orElse("0"));
		
		HttpSession session = request.getSession();
		if(session.getAttribute("open") == null) {
			session.setAttribute("open", true);
			
			int res = FreeDAO.getInstance().updateHit(freeNo);
			if(res == 0) {
				return new ActionForward("/BoardProject/list.do", true);
			}
			
		}

		Free board = FreeDAO.getInstance().selectBoardByFreeNo(freeNo);
		
		if(board != null) {
			session.setAttribute("board", FreeDAO.getInstance().selectBoardByFreeNo(freeNo));
			return new ActionForward("free/detail.jsp", false);
		}
		return null;
	}

}
