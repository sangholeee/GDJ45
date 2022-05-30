package com.goodee.ex13.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.goodee.ex13.domain.FreeBoardDTO;
import com.goodee.ex13.mapper.FreeBoardMapper;
import com.goodee.ex13.util.PageUtils;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardMapper freeBoardMapper;
	
	@Override
	public void findFreeBoards(HttpServletRequest request, Model model) {
		
		// totalRecord(DB), page(Parameter)
		int totalRecord = freeBoardMapper.selectFreeBoardCount();
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// PageEntity
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		// Map
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// 목록 가져오기
		List<FreeBoardDTO> freeBoards = freeBoardMapper.selectFreeBoardList(map);
		
		// free/list.jsp로 전달할 데이터
		model.addAttribute("freeBoards", freeBoards);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/freeBoard/list"));
		
	}

	@Override
	public int saveFreeBoard(HttpServletRequest request) {
		
		return 0;
	}

	@Override
	public int saveReply(HttpServletRequest request) {
		
		return 0;
	}

	@Override
	public int removeFreeBoard(Long freeBoardNo) {
		
		return 0;
	}

}
