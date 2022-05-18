package com.goodee.ex07.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex07.domain.BoardDTO;
import com.goodee.ex07.service.BoardService;

@Controller
public class BoardController {

	// 컨트롤러에서
	// HttpServletRequest, HttpServletResponse, HttpSession 을 선언할 수 있습니다.
	// service에서 쓰고 있는 것은 service에서 선언해서 쓴게 아니라 Controller에서 쓴 것을 받아온 것!
	
	
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
	
	
	@GetMapping("/board/addPage")
	public String addPage() {
		return "board/add";       // board/add.jsp로 이동
	}
	
	
	@PostMapping("/board/add")
	public void add(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		logger.info("add(): " + board);
		boardService.save(board, request, response);
		
		// 삽입 후에는 redirect를 해야 하는데,
		// redirect 코드가 없다는 것은 boardService의 save() 메소드에서 직접 이동했다는 것을 의미한다.
		// 응답을 만드는 response를 save() 메소드에 넘겨주면 save() 메소드에서 직접 이동할 수 있다.
	}
	
	@GetMapping("/board/remove")
	// @RequestParam(value="board_no", required = false, defaultValue = "0")
	// 파라미터 board_no가 오지 않는다면 0을 사용하겠습니다.
	public void remove(Long board_no, HttpServletRequest request, HttpServletResponse response) {
		logger.info("remove(): " + board_no);
		boardService.remove(board_no, request, response);
		// save() 메소드와 마찬가지로 remove() 메소드에서 직접 이동한다.
	}
	
	@PostMapping("/board/modifyPage")
	public String modifyPage(Model model, @RequestParam Long board_no) {
		BoardDTO board = boardService.findBoardByNo(board_no);
		logger.info("modifyPage(): " + board);
		model.addAttribute("board", board);
		return "board/modify";      // board/modify.jsp로 forward
	}
	
	/*   
						동일한 동작
	@PostMapping("/board/modifyPage")
	public String modifyPage(@ModelAttribute(value="board") BoardDTO board) {
		logger.info("modifyPage(): " + board);
		return "board/modify";      // board/modify.jsp로 forward
	}
	*/
	
	@PostMapping("/board/modify")
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		logger.info("modify(): " + board);
		boardService.modify(board, request, response);
		// save(), remove() 메소드처럼 modify() 메소드에서 직접 이동한다.
	}
	
}
