package com.goodee.ex06.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController2 {
	
	@Autowired
	private BoardService boardService;
	
	// logger
	// System.out.println() 대체
	private static final Logger logger = LoggerFactory.getLogger(BoardController2.class);
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		logger.info("list(): " + boards);  // 콘솔에 정보를 찍어준다.
		model.addAttribute("boards", boards);
		return "board/list";
	}
	
	@GetMapping("/detail")
	// @RequestParam(value="board_no", required=false, defaultValue="0")
	// 파라미터 board_no가 오지 않는다면 0을 사용하겠습니다.
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") Long board_no, Model model) {
		BoardDTO board = boardService.findBoardByNo(board_no);
		logger.info("detail(): " + board);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/addPage")
	public String addPage() {
		return "board/add";  // board/add.jsp로 이동
	}
	
	@PostMapping("/add")
	public String add(BoardDTO board) {
		logger.info("add(): " + board);
		boardService.save(board);
		// 삽입 후 목록보기로 redirect 하기 위해서
		// 목록보기의 매핑을 확인한다. (/board/list)
		// redirect는 매핑으로 이동하기 때문에 아래와 같이 작성한다.
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	// @RequestParam(value="board_no", required=false, defaultValue="0")
	// 파라미터 board_no가 오지 않는다면 0을 사용하겠습니다.
	public String remove(@RequestParam(value="board_no", required=false, defaultValue="0") Long board_no) {
		logger.info("remove(): " + board_no);
		boardService.remove(board_no);
		// 삭제 후에는 목록보기로 redirect 한다.
		return "redirect:/board/list";
	}
	
//	@PostMapping("/board/modifyPage")
//	public String modifyPage(BoardDTO board, Model model) {
//		logger.info("modifyPage(): " + board);
//		model.addAttribute("board", board);
//		return "board/modify";  // board/modify.jsp로 forward
//	}
	
	@PostMapping("/modifyPage")
	public String modifyPage(@ModelAttribute(value="board") BoardDTO board) {
		logger.info("modifyPage(): " + board);
		return "board/modify";  // board/modify.jsp로 forward
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO board) {
		logger.info("modify(): " + board);
		boardService.modify(board);
		// 수정 후에는 상세보기로 redirect 한다.
		return "redirect:/board/detail?board_no=" + board.getBoard_no();
	}
	
}
