package com.goodee.ex06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.goodee.ex06.domain.BoardDTO;

public class BoardRepository {

	// BoardRepository = BoardDAO
	// singleton 처리는 할 필요가 없다.
	// BoardConfig.java에 bean으로 등록해두면 스프링이 singleton으로 만들기 때문이다.
	
	// BoardConfig.java를 통해서 bean이 생성되는 순간
	// new BoardRepository()가 호출된다.
	// 외부에서 호출할 수 있도록 접근권한은 public으로 처리한다.
	
	public BoardRepository() {
		
	}

	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "1111");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// BoardRepository의 메소드 이름
	// DB와 직결되는 부분으로 select/insert/update/delete를 활용하는 것이 좋다.
	
	public List<BoardDTO> selectBoards(){
		List<BoardDTO> boards = new ArrayList<>();
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED, LASTMODIFIED FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();  
			while(rs.next()) {
				BoardDTO board = new BoardDTO(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}
	
	public BoardDTO selectBoardByNo(Long board_no) {
		BoardDTO board = null;
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED, LASTMODIFIED FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, board_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO();
				board.setBoard_no(rs.getLong("BOARD_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setWriter(rs.getString("WRITER"));
				board.setCreated(rs.getString("CREATED"));
				board.setLastModified(rs.getString("LASTMODIFIED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return board;
	}
	
	public int insertBoard(BoardDTO board) {
		int res = 0;
		try {
			con = getConnection();  
			sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATED, LASTMODIFIED) VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";    // 그냥 SYSDATE 는 DATE 타입이기 때문에 틀림.
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			res = ps.executeUpdate();  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	public int updateBoard(BoardDTO board) {
		int res = 0;
		try {
			con = getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, LASTMODIFIED = TO_CHAR(SYSDATE, 'YYYY-MM-DD' WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setLong(3, board.getBoard_no());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	public int deleteBoard(Long board_no) {
		int res = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, board_no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
