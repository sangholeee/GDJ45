package mybatis;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class ProductAddService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String realPath = request.getServletContext().getRealPath("storage");

		File dir = new File(realPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		File file = null;
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, 
									  realPath,
									  1024 * 1024 * 10, 
									  "UTF-8", 
									  new DefaultFileRenamePolicy());    // 중복처리
		} catch (IOException e) {
			error(file, response, "파일 첨부가 실패했습니다.");
		}
		
		file = mr.getFile("filename");
		String name = mr.getParameter("name");
		Integer price = Integer.parseInt(mr.getParameter("price"));
		String image = mr.getFilesystemName("filename");
		ProductDTO product = ProductDTO.builder()
				.name(name)
				.price(price)
				.image(image)
				.build();
		
		
		int res = ProductDAO.getInstance().insertProduct(product);
		
		ActionForward af = null;
		try {
			if(res > 0) {
			af = new ActionForward("/JUNIT/list.prod", true);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('제품 등록 실패");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return af;
		
	}

	public void error(File file, HttpServletResponse response, String msg) {
		if(file != null && file.exists()) {
			file.delete();
		}
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
