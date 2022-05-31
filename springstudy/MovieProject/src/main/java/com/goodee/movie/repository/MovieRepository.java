package com.goodee.movie.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.domain.QueryDTO;

@Repository
public class MovieRepository {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<MovieDTO> selectMovies() {
		return sqlSessionTemplate.selectList("mybatis.mapper.movie.selectMovies");
	}
	
	public List<MovieDTO> selectMoviesByQuery(QueryDTO query) {
		return sqlSessionTemplate.selectList("mybatis.mapper.movie.selectMoviesByQuery", query);
	}
	
}
