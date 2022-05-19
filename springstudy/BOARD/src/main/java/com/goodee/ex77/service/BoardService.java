package com.goodee.ex77.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodee.ex77.domain.BoardDTO;


public interface BoardService {

	public List<BoardDTO> findBoards();
	public BoardDTO findBoardByNo(Long no);
	public Long findBoardsCount();
	public Integer updateHit(Long no);
	public void save(BoardDTO board);           
	public void modify(BoardDTO board);
	public void remove(Long no, HttpServletRequest request, HttpServletResponse response);
	
}
