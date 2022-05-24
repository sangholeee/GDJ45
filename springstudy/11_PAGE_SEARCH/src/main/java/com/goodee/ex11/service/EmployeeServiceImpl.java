package com.goodee.ex11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.goodee.ex11.domain.Employee;
import com.goodee.ex11.mapper.EmployeeMapper;
import com.goodee.ex11.util.PageUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public void findEmployees(HttpServletRequest request, Model model) {
		
		// request에서 page 파라미터 꺼내기
		// page 파라미터는 전달되지 않는 경우 page = 1을 사용
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 전체 레코드 갯수 가져오기
		int totalRecord = employeeMapper.selectEmployeeCount();
		
		// totalRecord와 page를 알면 PageEntity를 모두 계산할 수 있다.
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		// beginRecord + endRecord => Map
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// beginRecord ~ endRecord 사이 목록 가져오기
		List<Employee> employees = employeeMapper.selectEmployees(map);
		
		// list.jsp로 forward할 때 가지고 갈 속성 저장하기
		model.addAttribute("employees", employees);
		model.addAttribute("paging", "1 2 3 4 5");
	}

}
