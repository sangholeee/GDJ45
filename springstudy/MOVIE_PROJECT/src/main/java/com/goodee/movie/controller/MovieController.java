package com.goodee.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("count", movieService.Count());
		return "index";
	}

	
	
	@GetMapping(value="/searchAllMovies")
	public ResponseEntity<List<MovieDTO>> searchAllMovies() {
		return movieService.findMovies();
	}
	
	
	@GetMapping(value="/searchMovie")
	public ResponseEntity<List<MovieDTO>> searchMovie(HttpServletRequest request) {
		return movieService.findMovieList(request);
	}
	
	
	
	
}
