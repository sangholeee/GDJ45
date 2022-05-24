package com.goodee.ex10.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex10.domain.NoticeDTO;
import com.goodee.ex10.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired       // @Autowired를 이용하려면 bean 이어야 하는데 bean이 되려면 Class여야 한다. 그런데 NoticeMapper는 interface이다.
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> findNotices() {
		return noticeMapper.selectNoticeList();
	}

	
	
	// 한 사람이 조회수를 누르면 최초 한 번만 증가하는 코드(생각)
	@Override
	public NoticeDTO findNoticeByNo(HttpServletRequest request) {
		
		// request.getRequestURI() : /ex09/notice/detail (상세보기를 위한 매핑값)
		String requestURI = request.getRequestURI();
		String[] arr = requestURI.split("/");                   // {"", "ex09", "notice", "detail"} -> 마지막 배열을 가져오는 법  arr[length-1]
		
		
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		
		if(arr[arr.length - 1].equals("detail")) {              // 상세보기 요청이면,
			noticeMapper.updateHit(noticeNo);			        // 조회수를 늘리고,
		}
		
		return noticeMapper.selectNoticeByNo(noticeNo);         // 정보를 조회한다.
	}

	@Override
	public int save(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeMapper.insertNotice(notice);
	}

	@Override
	public int change(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeNo(Long.parseLong(request.getParameter("noticeNo")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public int removeOne(HttpServletRequest request) {
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		return noticeMapper.deleteNotice(noticeNo);
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
			res += noticeMapper.deleteNotice(noticeNo);             // 하나라도 중간에 실패하면 아무것도 지우지 않은 것이 된다.(트랜잭션)
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
		return noticeMapper.deleteNoticeList(list);
	}
	
	@Override
	public void transaction() {
		// 성공하는 데이터
		noticeMapper.insertNotice(new NoticeDTO(null, "테스트", "테스트", null, null, null));
		
		// 실패하는 데이터
		noticeMapper.insertNotice(new NoticeDTO());

		
		
	}
	
}
