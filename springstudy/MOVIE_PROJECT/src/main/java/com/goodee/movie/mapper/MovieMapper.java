package com.goodee.movie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.movie.domain.MovieDTO;

@Mapper
public interface MovieMapper {

	public int selectMovieCount();
	public List<MovieDTO> selectMovieList();
	
	public List<MovieDTO> selectMovieBySearch(Map<String, Object> map);
	
}
