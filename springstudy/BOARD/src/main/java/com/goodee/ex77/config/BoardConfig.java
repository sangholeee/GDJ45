package com.goodee.ex77.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex77.repository.BoardRepository;
import com.goodee.ex77.service.BoardService;
import com.goodee.ex77.service.BoardServiceImpl;

@Configuration
public class BoardConfig {

	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public BoardRepository boardRepository() {
		return new BoardRepository();
	}
	
}
