package com.goodee.movie;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goodee.movie.config.DBConfig;
import com.goodee.movie.config.MovieConfig;
import com.goodee.movie.repository.MovieRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MovieConfig.class, DBConfig.class})
public class MovieTestCase {

	@Autowired
	private MovieRepository movieRepository;
	
	@Test
	public void 영화목록테스트() {
		assertEquals(10, movieRepository.selectMovies().size());
	}

}
