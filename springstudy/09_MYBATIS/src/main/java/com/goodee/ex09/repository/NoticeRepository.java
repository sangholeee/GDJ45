package com.goodee.ex09.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRepository {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	
	
}
