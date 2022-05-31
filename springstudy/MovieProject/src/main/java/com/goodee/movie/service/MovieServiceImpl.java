package com.goodee.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.movie.domain.QueryDTO;
import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<MovieDTO> findMovies() {
		return movieRepository.selectMovies();
	}
	
	@Override
	public List<MovieDTO> findMoviesByQuery(QueryDTO query) {
		return movieRepository.selectMoviesByQuery(query);
	}
	
}
