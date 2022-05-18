package com.goodee.ex77.service;

import java.util.List;


import com.goodee.ex77.domain.BoardDTO;


public interface BoardService {

	public List<BoardDTO> findBoards();
	public BoardDTO findBoardByNo(Long no);
	public void save(BoardDTO board);           
	public void modify(BoardDTO board);
	public void remove(Long no);
	
}
