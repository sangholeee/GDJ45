package com.goodee.ex09.service;

import java.util.List;

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

	@Override
	public NoticeDTO findNoticeByNo(Long noticeNo) {
		noticeRepository.updateHit(noticeNo);					// 조회수를 늘리고,
		return noticeRepository.selectNoticeByNo(noticeNo);     // 정보를 조회한다.
	}

	@Override
	public int save(NoticeDTO notice) {
		return noticeRepository.insertNotice(notice);
	}

	@Override
	public int change(NoticeDTO notice) {
		
		return 0;
	}

	@Override
	public int remove(Long noticeNo) {
		
		return 0;
	}

}
