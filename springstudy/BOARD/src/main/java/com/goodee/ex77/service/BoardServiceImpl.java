package com.goodee.ex77.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.ex77.repository.BoardRepository;
import com.goodee.ex77.domain.BoardDTO;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<BoardDTO> findBoards() {
		return boardRepository.selectBoards();
	}

	@Override
	public BoardDTO findBoardByNo(Long no) {
		return boardRepository.selectBoardByNo(no);
		return boardRepository.updateHit(no);
	}

	@Override
	public void save(BoardDTO board) {
		boardRepository.insertBoard(board);
	}

	@Override
	public void modify(BoardDTO board) {
		boardRepository.updateBoard(board);
	}

	@Override
	public void remove(Long no) {
		boardRepository.deleteBoard(no);
	}

}
