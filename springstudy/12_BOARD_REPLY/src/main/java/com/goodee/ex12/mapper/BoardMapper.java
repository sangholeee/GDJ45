package com.goodee.ex12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex12.domain.BoardDTO;

@Mapper
public interface BoardMapper {

	public int selectBoardCount();
	public List<BoardDTO> selectBoardList(Map<String, Object> map);
	
	public BoardDTO selectBoardByNo(Long boardNo);
	public int updateBoardHit(Long boardNo);
	
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(Long boardNo);
	
}
