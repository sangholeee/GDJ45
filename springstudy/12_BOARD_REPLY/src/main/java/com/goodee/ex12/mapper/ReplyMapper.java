package com.goodee.ex12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex12.domain.ReplyDTO;

@Mapper
public interface ReplyMapper {
	
	public int selectReplyCount();
	public List<ReplyDTO> selectReplyList(Map<String, Object> map);
	
	public int insertReply(ReplyDTO reply);
	public int deleteReply(Long replyNo);
	
}
