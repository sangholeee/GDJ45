package mybatis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductRemoveService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long productNo = 0L;
		String strNo = request.getParameter("productNo");
		if(strNo == null || strNo.isEmpty()) {
			productNo = 0L;
		} else {
			productNo = Long.parseLong(strNo);
		}
		
		int res = ProductDAO.getInstance().deleteProduct(productNo);
		
		/* 삭제 성공 메시지 가지고 이동 */
		try {
			if(res > 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제되었습니다.')");
				out.println("location.href='/JUNIT/list.prod'");
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
