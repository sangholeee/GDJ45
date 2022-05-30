package com.goodee.ex13.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex13.domain.FreeBoardDTO;

@Mapper
public interface FreeBoardMapper {

	// 목록
	public int selectFreeBoardCount();
	public List<FreeBoardDTO> selectFreeBoardList(Map<String, Object> map);
	
	// 게시글 삽입
	public int insertFreeBoard(FreeBoardDTO freeBoard);
	
	// 댓글 삽입(트랜잭션)
	public int updatePreviousReply(FreeBoardDTO freeBoard);
	public int insertReply(FreeBoardDTO freeBoard);
	
	// 삭제
	public int deleteFreeBoard(Long freeBoardNo);
	
}
