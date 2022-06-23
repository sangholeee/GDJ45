package com.goodee.naver3.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.naver3.domain.NaverDTO;

@Mapper
public interface NaverMapper {

	public int insertNaver(NaverDTO naver);

}
