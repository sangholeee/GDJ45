package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Staff;

public class StaffDAO {

	private SqlSessionFactory factory;
	private static StaffDAO instance = new StaffDAO();
	private StaffDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static StaffDAO getInstance() {
		return instance;
	}
	
	public int insertStaff(Staff staff) throws PersistenceException {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("mybatis.mapper.staff.insertStaff", staff);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	public List<Staff> selectStaffList() {
		SqlSession ss = factory.openSession();
		List<Staff> list = ss.selectList("mybatis.mapper.staff.selectStaffList");
		ss.close();
		return list;
	}
	
	public Staff selectStaffBySno(String sno) {
		SqlSession ss = factory.openSession();
		Staff staff = ss.selectOne("mybatis.mapper.staff.selectStaffBySno", sno);
		ss.close();
		return staff;
	}
	
}
