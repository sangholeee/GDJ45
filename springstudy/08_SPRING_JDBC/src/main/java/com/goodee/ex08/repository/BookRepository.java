package com.goodee.ex08.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.goodee.ex08.domain.BookDTO;

@Repository           // 있어도 되고 없어도 된다.
public class BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;       // Connection, PreparedStatement, ResultSet을 내부에서 사용하는 객체
	
	private String sql;
	
	public List<BookDTO> selectBookList() {
		sql = "SELECT BOOK_NO, TITLE, AUTHOR, PRICE, PUBDATE, REGDATE FROM BOOK ORDER BY BOOK_NO DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookDTO.class));                          // 모든 row를 반환한다. , 뒤에 붙이면 전달할 값도 전달 가능한다.
	}
	
	public BookDTO selectBookByNo(final Long book_no) {    // 과거에는 final이 매개변수에 안적혀있으면 동작하지 않던 때가 있었다.
		sql = "SELECT BOOK_NO, TITLE, AUTHOR, PRICE, PUBDATE, REGDATE FROM BOOK WHERE BOOK_NO = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookDTO.class), book_no);        // queryForObject : 객체 하나 반환한다.
	}
	
	public int insertBook(final BookDTO book) {
		return jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				sql = "INSERT INTO BOOK VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getAuthor());
				ps.setInt(3, book.getPrice());
				ps.setString(4, book.getPubdate());
				return ps;
			}
		});
	}
	
	public int updateBook(BookDTO book) {
		sql = "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, PRICE = ? WHERE BOOK_NO = ?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getAuthor());
				ps.setInt(3, book.getPrice());
				ps.setLong(4, book.getBook_no());
			}
		});
	}
	
	public int deleteBook(Long book_no) {
		sql = "DELETE FROM BOOK WHERE BOOK_NO = ?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, book_no);
			}
		});
	}
	
	
	
}
