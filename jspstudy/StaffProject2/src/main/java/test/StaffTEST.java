package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Staff;
import repository.StaffDAO;

public class StaffTEST {

	@BeforeEach
	void setUp() {
		Staff staff = new Staff();
		staff.setSno("99999");
		staff.setName("김기획");
		staff.setDept("기획부");
		staff.setSalary(5000);
		int res = 0;
		try {
			res = StaffDAO.getInstance().insertStaff(staff);
		} catch (Exception e) { }
		assertEquals(1, res, "사원 등록에 문제가 있습니다.");
	}

	@Test
	void 조회테스트() {
		Staff staff = StaffDAO.getInstance().selectStaffBySno("99999");
		assertNotNull(staff, "사원 검색에 문제가 있습니다.");
	}

}
