package com.goodee.ex77.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String list(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		model.addAttribute("boards", boards);
		return "board/list";
	}
	
	@GetMapping("/board/detail")
	public String detail(@RequestParam(value="no", required=false, defaultValue="0") Long no, Model model) {
		BoardDTO board = boardService.findBoardByNo(no);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/board/addPage")
	public String addPage() {
		return "board/add";  
	}
	
	@PostMapping("/board/add")
	public String add(BoardDTO board) {
		logger.info("add(): " + board);
		boardService.save(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/remove")
	public String remove(@RequestParam(value="no", required=false, defaultValue="0") Long no) {
		boardService.remove(no);
		return "redirect:/board/list";
	}
	
	
	@PostMapping("/board/modifyPage")
	public String modifyPage(@ModelAttribute(value="board") BoardDTO board) {
		return "board/modify"; 
	}
	
	@PostMapping("/board/modify")
	public String modify(BoardDTO board) {
		boardService.modify(board);
		// 수정 후에는 상세보기로 redirect 한다.
		return "redirect:/board/detail?no=" + board.getNo();
	}
	
}
