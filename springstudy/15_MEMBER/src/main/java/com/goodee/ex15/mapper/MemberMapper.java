package com.goodee.ex15.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex15.domain.MemberDTO;

@Mapper
public interface MemberMapper {

	public MemberDTO selectMemberById(String id);
	public MemberDTO selectMemberByEmail(String email);
	
}
