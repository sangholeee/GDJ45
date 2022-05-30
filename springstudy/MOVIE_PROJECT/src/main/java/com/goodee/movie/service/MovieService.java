package com.goodee.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.goodee.movie.domain.MovieDTO;

public interface MovieService {

	public int Count();
	public ResponseEntity<List<MovieDTO>> findMovies();
	
	public ResponseEntity<List<MovieDTO>> findMovieList(HttpServletRequest request);
	
}
