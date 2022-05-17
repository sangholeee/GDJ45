package com.goodee.ex06;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goodee.ex06.config.BoardConfig;
import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.repository.BoardRepository;

// 안녕. 난 JUnit4 단위 테스트를 할 거야.
@RunWith(SpringJUnit4ClassRunner.class)

// 안녕. 단위 테스트를 할 때 여기 있는 bean을 만들어서 수행해줘
@ContextConfiguration(classes = {BoardConfig.class})

// root-context.xml로 bean을 만들었으면 아래처럼 하면 되지.
// @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class BoardTestCase {

	// BoardRepository가 필요해요. (DI)
	@Autowired
	private BoardRepository boardRepository;
	
	// 단위 테스트의 메소드 이름은 "한글"로 해도 좋아요.
	
	@Test
	public void 목록() {
		List<BoardDTO> boards = boardRepository.selectBoards();
		assertEquals(0, boards.size());
	}

}
