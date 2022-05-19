package com.goodee.ex77.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.goodee.ex77.domain.BoardDTO;

public class BoardRepository {

	public BoardRepository() {
		
	}
	
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "1111");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
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
	
	
	public List<BoardDTO> selectBoards(){
		List<BoardDTO> boards = new ArrayList<>();
		try {
			con = getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, IP, HIT, CREATED, LASTMODIFIED FROM BOARD ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO(
						rs.getLong(1),   
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),  
						rs.getLong(6),    
						rs.getDate(7), 
						rs.getDate(8)); 
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}
	
	public Long selectBoardCount() {
		Long count = 0L;
		try {
			con = getConnection();
			sql = "SELECT COUNT(*) AS 갯수 FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getLong("갯수");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return count;
	}
	
	public BoardDTO selectBoardByNo(Long no) {
		BoardDTO board = null;
		try {
			con = getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, IP, HIT, CREATED, LASTMODIFIED FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO(
						rs.getLong(1),   
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),  
						rs.getLong(6),    
						rs.getDate(7), 
						rs.getDate(8)); 
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
			sql = "INSERT INTO BOARD(NO, WRITER, TITLE, CONTENT, IP, HIT, CREATED, LASTMODIFIED) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE)";
			// 날짜 관련 타입이 sql에서 선언이 TIMESTAMP로 되어 있으면 SYSTIMESTAMP로 작성해줘야 한다.
			// BoardDTO에서는 Timestamp(java.sql)로 해주면 된다.
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			ps.setString(4, board.getIp());
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
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, LASTMODIFIED = SYSDATE WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setLong(3, board.getNo());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	public int updateHit(Long no) {
		int res = 0;
		try {
			con = getConnection();
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	public int deleteBoard(Long no) {
		int res = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
}
