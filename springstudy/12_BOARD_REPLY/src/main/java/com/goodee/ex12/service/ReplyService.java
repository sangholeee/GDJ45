package com.goodee.ex12.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ReplyService {
	
	// ajax 처리하려면 반환 타입이 달라진다.
	// 값을 반환해야한다.
	// ResponseEntity or Map 이 좋다.
	

	public Map<String, Object> findReplies(Long boardNo);
	
	public Map<String, Object> saveReply(HttpServletRequest request);
	
	public Map<String, Object> removeReply(Long replyNo);
	
}
