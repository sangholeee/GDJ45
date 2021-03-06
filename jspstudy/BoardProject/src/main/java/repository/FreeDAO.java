package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Free;

public class FreeDAO {

	private SqlSessionFactory factory;
	
	private static FreeDAO instance = new FreeDAO();
	private FreeDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static FreeDAO getInstance() {
		return instance;
	}
	
	// 목록
	public List<Free> selectBoardList() {
		SqlSession ss = factory.openSession();
		List<Free> list = ss.selectList("mybatis.mapper.free.selectBoardList");
		ss.close();
		return list;
	}
	
	// 삽입
	public int insertBoard(Free board) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.free.insertBoard", board);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 삭제
	public int deleteBoard(Long freeNo) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.mapper.free.deleteBoard", freeNo);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 상세
	public Free selectBoardByFreeNo(Long freeNo) {
		SqlSession ss = factory.openSession();
		Free board = ss.selectOne("mybatis.mapper.free.selectBoardByFreeNo", freeNo);
		ss.close();
		return board;
	}
	
	// 수정
	public int updateBoard(Free board) {
		SqlSession ss = factory.openSession(false);
		int res = ss.update("mybatis.mapper.free.updateBoard", board);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 조회수
	public int updateHit(Long freeNo) {
		SqlSession ss = factory.openSession();
		int res = ss.update("mybatis.mapper.free.updateHit", freeNo);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 탑 조회수 
	public List<Free> selectBoardTopHit() {
		SqlSession ss = factory.openSession();
		List<Free> list = ss.selectList("mybatis.mapper.free.selectBoardTopHit");
		ss.close();
		return list;
	}

	
	
}
