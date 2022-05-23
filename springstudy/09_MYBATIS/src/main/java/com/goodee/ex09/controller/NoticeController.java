package com.goodee.ex09.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.ex09.domain.NoticeDTO;
import com.goodee.ex09.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/notice/list")
	public String list(Model model) {
		model.addAttribute("notices", noticeService.findNotices());
		return "notice/list";        // notice 폴더 아래 list.jsp로 이동
	}
	
	@GetMapping("/notice/savePage")
	public String savePage() {
		return "notice/save";        // notice 폴더 아래 save.jsp로 이동
	}
	
	@PostMapping("/notice/save")
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {     
		// 작성자의 IP를 받을 때에는 request를 써야 한다.           
		// 파라미터 받는 3가지 방법 1. HttpServletRequest request, 2. @RequestParam String title 3. NoticeDTO notice
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		int res = noticeService.save(notice);
		
		// 성공/실패 메시지 처리가 없는 경우
		// return "redirect:/notice/list";    // redirect는 매핑으로 이동한다. 목록보기매핑(/notice/list)
		
		// 성공/실패 메시지 처리를 담당하는 result.jsp를 만들고, 
		// result.jsp로 redirect 이동하는 방법을 사용한다. 
		// result.jsp로 성공/실패 유무, 작업종류를 보내줘야 하는데,
		// redirect로 이동하는 경우에는 RedirectAttributes에 정의된 addFlashAttribute() 메소드를 
		// 이용해 값을 전달할 수 있다.
		redirectAttributes.addFlashAttribute("kind", "insert");    // insert -> ${kind}
		redirectAttributes.addFlashAttribute("res", res);          // res    -> ${res} 
		
		return "redirect:/notice/afterDML";
		
	}

	@GetMapping("/notice/afterDML")
	public String afterDML() {
		return "notice/result";       // notice 폴더 아래 result.jsp를 의미한다.
	}
	
	@GetMapping("/notice/detail")
	public String detail(@RequestParam Long noticeNo, Model model) {
		model.addAttribute("notice", noticeService.findNoticeByNo(noticeNo));
		return "notice/detail";       // notice 폴더 아래 detail.jsp를 의미한다.
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
