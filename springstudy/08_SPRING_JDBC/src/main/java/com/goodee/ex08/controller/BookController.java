package com.goodee.ex08.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex08.domain.BookDTO;
import com.goodee.ex08.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/book/list") 
	public String list(Model model){
		model.addAttribute("books", bookService.findBooks());
		return "book/list";
	}
	
	@GetMapping("/book/detail")
	public String detail(@RequestParam(value="book_no") Long book_no, Model model) {
		model.addAttribute("book", bookService.findBookByNo(book_no));
		return "book/detail";
	}
	
	@GetMapping("/book/savePage")
	public String savePage() {
		return "book/save";
	}
	
	// 성공, 실패 메시지 작성
	@PostMapping("/book/save")
	public void save(BookDTO book, HttpServletRequest request, HttpServletResponse response) {
		int res = bookService.save(book);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/book/list'");  
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('등록되지 않았습니다..')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 성공, 실패 메시지 작성
	@PostMapping("/book/change")
	public void change(BookDTO book, HttpServletRequest request, HttpServletResponse response) {
		int res = bookService.change(book);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/book/list'");  
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('수정되지 않았습니다..')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 성공, 실패 메시지 작성
	@GetMapping("/book/remove")
	public void remove(@RequestParam(value="book_no", required=false, defaultValue="0") Long book_no, 
						HttpServletRequest request, HttpServletResponse response) {
		int res = bookService.remove(book_no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/book/list'");  
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삭제되지 않았습니다..')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 트랙잭션 테스트용
	@GetMapping("/book/transaction/test")
	public String transaction() {
		bookService.transaction();
		return "redirect:/book/list";
	}
	
	
}
