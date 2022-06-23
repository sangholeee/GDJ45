package com.goodee.ex01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex01.mapper.BbsMapper;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsMapper bbsMapper;
	
}
