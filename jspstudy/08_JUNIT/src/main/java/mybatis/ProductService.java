package mybatis;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProductService {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
}
