package com.goodee.ex77.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex77.domain.BoardDTO;
import com.goodee.ex77.service.BoardService;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/list")
	public String list(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		List<BoardDTO> boards = boardService.findBoards();
		model.addAttribute("totalCount", boardService.findBoardsCount());
		model.addAttribute("boards", boards);
		return "board/list";
	}
	
	@GetMapping("/board/detail")
	public String detail(@RequestParam(value="no", required=false, defaultValue="0") Long no, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("open") == null) {
			session.setAttribute("open", true);
			int res = boardService.updateHit(no);
			if(res == 0) {
				return "redirect:/board/list";
			}
		}
		
		BoardDTO board = boardService.findBoardByNo(no);
		
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/board/addPage")
	public String addPage() {
		return "board/add";  
	}
	
	@PostMapping("/board/add")
	public String add(BoardDTO board, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		board.setIp(ip);
		// board.setIp(request.getRemoteAddr(); 하면 한 줄로 가능!
		logger.info("add(): " + board);
		boardService.save(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/remove")
	public void remove(@RequestParam(value="no", required=false, defaultValue="0") Long no, HttpServletRequest request, HttpServletResponse response) {
		boardService.remove(no, request, response);
		// return "redirect:/board/list";      BoardServiceImpl에서 이동을 명령했기 때문에 여기서 또 할 필요가 없다!
	}
	
	@PostMapping("/board/modify")
	public String modify(BoardDTO board) {
		boardService.modify(board);
		return "redirect:/board/detail?no=" + board.getNo();
	}
	
}
