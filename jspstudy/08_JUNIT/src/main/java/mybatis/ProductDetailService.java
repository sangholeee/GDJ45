package mybatis;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductDetailService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Optional<String> opt = Optional.ofNullable(request.getParameter("productNo"));
		Long productNo = Long.parseLong(opt.orElse("0"));
		request.setAttribute("product", ProductDAO.getInstance().selectProductByProductNo(productNo));
		request.setAttribute("contextPath", request.getContextPath());
				
		return new ActionForward("product2/detail.jsp", false);
	}

}
