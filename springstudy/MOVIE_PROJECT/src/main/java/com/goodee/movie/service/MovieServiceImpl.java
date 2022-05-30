package com.goodee.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.mapper.MovieMapper;
import com.goodee.movie.util.SecurityUtils;


public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	@Override
	public int Count() {
		int count = movieMapper.selectMovieCount();
		return count;
	}
	
	@Override
	public ResponseEntity<List<MovieDTO>> findMovies() {
		
		ResponseEntity<List<MovieDTO>> responseEntity = null;
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		
		List<MovieDTO> movie = movieMapper.selectMovieList();
		
	
		responseEntity = new ResponseEntity<List<MovieDTO>>(movie, header, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<List<MovieDTO>> findMovieList(HttpServletRequest request) {
		
		ResponseEntity<List<MovieDTO>> responseEntity = null;
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		
		String column = request.getParameter("column");
		String searchText = SecurityUtils.XSS(request.getParameter("searchText"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("column", column);
		map.put("searchText", searchText);
		
		List<MovieDTO> movie = movieMapper.selectMovieBySearch(map);
		
		if(movie.isEmpty()) {
			responseEntity = new ResponseEntity<List<MovieDTO>>(null, header, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = new ResponseEntity<List<MovieDTO>>(movie, header, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	

}
