package com.goodee.ex12.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex12.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	

}
