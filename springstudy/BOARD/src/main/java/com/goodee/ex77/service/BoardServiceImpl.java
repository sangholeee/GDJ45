package com.goodee.ex77.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void remove(Long no, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.deleteBoard(no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list'");   // 여기서 이동을 명령했는데 Controller에서 또 redirect 하라고 하니까 오류남!        
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삭제되지 않았습니다..')");
				out.println("history.back()");         
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Long findBoardsCount() {
		return boardRepository.selectBoardCount();
	}
	
	@Override
	public Integer updateHit(Long no) {
		return boardRepository.updateHit(no);
	}

}
