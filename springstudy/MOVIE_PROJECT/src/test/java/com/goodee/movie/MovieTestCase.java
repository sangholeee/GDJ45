package com.goodee.movie;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goodee.movie.config.ServiceConfig;
import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.service.MovieService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ServiceConfig.class})

public class MovieTestCase {

	@Autowired
	private MovieService movieService;
	
	@Test
	public void 영화목록테스트() {
		ResponseEntity<List<MovieDTO>> movies = movieService.findMovies();		
		assertEquals(10, movies);
	}

}
