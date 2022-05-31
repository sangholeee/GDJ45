package com.goodee.movie.service;

import java.util.List;

import com.goodee.movie.domain.QueryDTO;
import com.goodee.movie.domain.MovieDTO;

public interface MovieService {
	public List<MovieDTO> findMovies();
	public List<MovieDTO> findMoviesByQuery(QueryDTO query);	
}
