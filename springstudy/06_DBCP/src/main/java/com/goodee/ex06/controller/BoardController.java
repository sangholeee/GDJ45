package com.goodee.ex06.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.service.BoardService;

@Controller
public class BoardController {

	// logger
	// System.out.println() 대체
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// BoardService boardService : DI (BoardConfig.java에 저장된 bean 가져오기)
	// 1. 필드 주입   : @Autowired private BoardService boardService;
	// 2. 생성자 주입 : BoardController에 @AllArgConstructor 추가
	// 3. 세터 주입   : @setter 코드를 추가한 뒤 @Autowired 추가(@Setter 사용 불가)
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/list")
	public String list(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		logger.info("list(): " + boards);
		model.addAttribute("boards", boards);
		return "/board/list";

	}
	

	@GetMapping("/board/detail")
	public String detail(Model model, @RequestParam Long board_no) {
		BoardDTO board = boardService.findBoardByNo(board_no);
		logger.info("detail(): " + board);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	
	@PostMapping("/board/insert")
	public String insert(Model model, BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
				
		String res = boardService.save(board, request, response);
		model.addAttribute("board", board);
		
		return "/board/list";
	}
	
	
	
	
	
	
	
	
	
}
