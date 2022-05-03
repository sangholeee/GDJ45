package model;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lotto implements MyService {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		Set<Integer> lotto = new HashSet<>();
		while(lotto.size() != 6) {
			lotto.add((int)(Math.random() * 45) +1);
		}
				
		request.setAttribute("result", lotto.toString());
		
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp");
		af.setRedirect(false);
		
		// ActionForward 반환
		return af;
		
	}

}
