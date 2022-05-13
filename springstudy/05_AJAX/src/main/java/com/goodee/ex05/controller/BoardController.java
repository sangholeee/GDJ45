package com.goodee.ex05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.ex05.domain.BoardDTO;
import com.goodee.ex05.service.BoardService;

/*
	@RestController
	
	안녕. 난 Ajax 처리를 위한 컨트롤러야.
	날 선언하면 모든 메소드에 @ResponseBody 애너테이션을 자동으로 붙여줄게.
	개발자 너희는 붙이지 마.
 */

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	
	@GetMapping(value="/board/detail1",
				produces="application/json; charset=UTF-8")
	// @ResponseBody       @RestController를 사용하면 안 적어요~
	
	public BoardDTO detail1(HttpServletRequest request) {
		
		BoardDTO board = boardService.detail1(request);
		
		return board;
	}
	
	
	@GetMapping(value="/board/detail2",
				produces="application/json; charset=UTF-8")
	// @ResponseBody       @RestController를 사용하면 안 적어요~
	
	public BoardDTO detail2(@RequestParam(value="title") String title,
							@RequestParam(value="hit") Long hit) {
		
		BoardDTO board = boardService.detail2(title, hit);
		
		return board;
		
	}
	
	
	@PostMapping(value="/board/detail3",
				produces="application/json; charset=UTF-8")
	// @ResponseBody       @RestController를 사용하면 안 적어요~
	
	public Map<String, Object> detail3(@RequestBody BoardDTO board) {
		
		Map<String, Object> res = boardService.detail3(board);
		
		return res;
		
	}
	
	@PostMapping(value="/board/detail4",                      
				produces="application/json; charset=UTF-8")


	public BoardDTO detail4(@RequestBody Map<String, Object> map) {
	
	BoardDTO board = boardService.detail4(map);
	
	return board;    
}
	

}
