package com.goodee.ex12.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.ex12.mapper.BoardMapper;

// Scheduler! 
@Component  // 안녕. 난 bean이야.
public class BoardJob {

	@Autowired
	private BoardMapper boardMapper;
	
	
	@Scheduled(cron = "0/10 * * * * ?")  // 크론식 -> 10초마다 동작 (JSP 07_BATCH / StudentLitsenr) 
	public void execute() {
		System.out.println("---쿼츠 동작 중---");
		System.out.println(boardMapper.selectBoardCount());
	}
	
	// 처리 후 DBConfig 작업
	
}
