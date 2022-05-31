package com.goodee.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.movie.repository.MovieRepository;
import com.goodee.movie.service.MovieService;
import com.goodee.movie.service.MovieServiceImpl;

@Configuration
public class MovieConfig {

	@Bean
	public MovieService movieService() {
		return new MovieServiceImpl();
	}
	
	@Bean
	public MovieRepository movieRepository() {
		return new MovieRepository();
	}
	
}
