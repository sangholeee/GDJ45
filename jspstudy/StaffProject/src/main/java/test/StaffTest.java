package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.StaffDTO;
import repository.StaffDAO;


public class StaffTest {
	
		// @BeforeEach   
		void 등록테스트() throws Exception {
			
			StaffDTO staff = StaffDTO.builder()
					.sno("77777")
					.name("강동원")
					.dept("기획부")
					.salary(5000L)
					.build();
			int res = 0;
			
			
			try {
				
				res = StaffDAO.getInstance().insertStaff(staff);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
					
			assertEquals(1, res, "사원 등록에 문제가 있습니다.");
			
		}
		
		@Test
		void 조회테스트() {
			
			StaffDTO staff = StaffDAO.getInstance().selectStaffByNo("99999");
			assertEquals("99999", staff.getSno(), "사원 검색에 문제가 있습니다.");
			
		}
}
