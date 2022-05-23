package com.goodee.ex09.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex09.domain.NoticeDTO;
import com.goodee.ex09.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Override
	public List<NoticeDTO> findNotices() {
		return noticeRepository.selectNoticeList();
	}

	
	
	// 한 사람이 조회수를 누르면 최초 한 번만 증가하는 코드(생각)
	@Override
	public NoticeDTO findNoticeByNo(HttpServletRequest request) {
		
		// request.getRequestURI() : /ex09/notice/detail (상세보기를 위한 매핑값)
		String requestURI = request.getRequestURI();
		String[] arr = requestURI.split("/");                   // {"", "ex09", "notice", "detail"} -> 마지막 배열을 가져오는 법  arr[length-1]
		
		
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		
		if(arr[arr.length - 1].equals("detail")) {              // 상세보기 요청이면,
			noticeRepository.updateHit(noticeNo);			    // 조회수를 늘리고,
		}
		
		return noticeRepository.selectNoticeByNo(noticeNo);     // 정보를 조회한다.
	}

	@Override
	public int save(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeRepository.insertNotice(notice);
	}

	@Override
	public int change(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeNo(Long.parseLong(request.getParameter("noticeNo")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeRepository.updateNotice(notice);
	}

	@Override
	public int removeOne(HttpServletRequest request) {
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		return noticeRepository.deleteNotice(noticeNo);
	}
	
	@Override
	public int removeList(HttpServletRequest request) {
		// 하나씩 여러 번 지우기
		// DELETE FROM NOTICE WHERE NOTICE_NO = 1
		// DELETE FROM NOTICE WHERE NOTICE_NO = 4
		String[] noticeNoList = request.getParameterValues("noticeNoList");    // {"1", "4"}
		Long res = 0L;
		for(int i = 0; i < noticeNoList.length; i++) {
			Long noticeNo = Long.parseLong(noticeNoList[i]);
			res += noticeRepository.deleteNotice(noticeNo);         // 하나라도 중간에 실패하면 아무것도 지우지 않은 것이 된다.(트랜잭션)
		}
		return (res == noticeNoList.length) ? 1 : 0;    			// 모두 삭제했다면 1 반환 아니면 0 반환

	}

	@Override
	public int removeList2(HttpServletRequest request) {
		// 한 번에 여러 개 지우기
		// DELETE FROM NOTICE WHERE NOTICE_NO IN(1, 4)
		String[] noticeNoList = request.getParameterValues("noticeNoList");    // {"1", "4"}
		List<Long> list = new ArrayList<>();
		for(int i = 0; i < noticeNoList.length; i++) {
			list.add(Long.parseLong(noticeNoList[i]));       // list.add(1) -> list.add(4)
		}
		return noticeRepository.deleteNoticeList(list);
	}
	
}
