package com.goodee.ex05.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goodee.ex05.domain.BoardDTO;

public class BoardServiceImpl implements BoardService {

	public BoardDTO detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		Long hit = Long.parseLong(request.getParameter("hit"));
		
		BoardDTO board = new BoardDTO(title, hit);
		
		return board;
	}
	
	public BoardDTO detail2(String title, Long hit) {
		
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setHit(hit);
		
		return board;
		
	}
	
	@Override
	public Map<String, Object> detail3(BoardDTO board) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", board.getTitle());
		map.put("hit", board.getHit());
		
		return map;
	}
	
	@Override
	public BoardDTO detail4(Map<String, Object> map) {
		
		BoardDTO board = new BoardDTO();
		board.setTitle(map.get("title").toString());                  
		board.setHit(Long.parseLong(map.get("hit").toString()));					
		
		return board; 
	}
	
}
