package com.goodee.ex12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.ex12.domain.BoardDTO;
import com.goodee.ex12.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("board");     // board에 관한 session만 초기화 시켜준다.
		// session.invalidate();                           // Session을 전체 초기화 시켜준다.
		boardService.findBoards(request, model);
		return "board/list";
	}
	
	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}
	
	// redirect 할 때는 model을 못쓴다.
	// 1. 반환타입 void -> Service에서 직접 이동하고 성공, 실패 메시지를 만든다. // 매개변수 : (HttpServletResponse response) 
	// 2. 반환타입 String -> return "redirect:/board/list" -> 메시지 없이 이동
	// 3. 반환타입 String -> return "redirect:/board/result" -> result.jsp 에서 성공 실패 메시지를 만든다. // 매개변수 : (RedirectAttributes redirectAttributes) 여기에 0 or 1을 저장해서 넘김! 
	
	@PostMapping("/board/save")
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("kind", "insert");    // insert -> ${kind}
		redirectAttributes.addFlashAttribute("insRes", boardService.save(request));
		return "redirect:/board/result";    // 매핑 /board/result로 redirect 하겠다.
	}
	
	@GetMapping("/board/result")
	public String result() {
		return "board/result";              // board/result.jspt로 이동하겠다.
	}
	
	@GetMapping("/board/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model) {
		boardService.findBoardByNo(request, response, model);
		return "board/detail";
	}
	
	@GetMapping("/board/changePage")
	public String changePage() {
		return "board/change";
	}
	
	@PostMapping("/board/change")
	public String change(BoardDTO board, RedirectAttributes redirectAttributes) {
		/* 매개변수로 request를 받으면 아래 builder()를 작성해야한다.
		   boardDTO를 매개변수로 받아 통째로 넘기면 편함!
		BoardDTO board = BoardDTO.builder()
				.boardNo(Long.parseLong(request.getParameter("boardNo")))
				.title(request.getParameter("title"))
				.content(request.getParameter("content"))
				.build();
		*/
		redirectAttributes.addFlashAttribute("kind", "update");
		redirectAttributes.addFlashAttribute("updRes", boardService.change(board));
		return "redirect:/board/result";
	}
	
	@GetMapping("/board/remove")
	public String remove(@RequestParam(value="boardNo", required=false, defaultValue = "0L") Long boardNo
				, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("kind", "delete");
		redirectAttributes.addFlashAttribute("delRes", boardService.remove(boardNo));
		return "redirect:/board/result";
	}
}
