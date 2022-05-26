package com.goodee.ex12.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.goodee.ex12.domain.BoardDTO;

public interface BoardService {
	
	public void findBoards(HttpServletRequest request, Model model);  
	// model이 없을 때는 List를 반환, controller에서 List를 받는다.
	// model이 있으면 model이 다 가지고 간다.
	// 전달하는 내용이 많을 때 model을 사용하면 편함!
	
	public void findBoardByNo(HttpServletRequest request, HttpServletResponse response, Model model);
	
	public int save(HttpServletRequest request);    // 작성 IP를 위해 request가 필요하다.
	public int change(BoardDTO board);
	public int remove(Long boardNo);

}
