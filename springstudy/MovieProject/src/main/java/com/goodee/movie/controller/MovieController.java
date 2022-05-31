package com.goodee.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.domain.QueryDTO;
import com.goodee.movie.service.MovieService;
import com.goodee.movie.util.SecurityUtils;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping(value="searchAllMovies", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> searchAllMovies() {
		List<MovieDTO> list = movieService.findMovies();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("message", "목록이 없습니다.");
			resultMap.put("list", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("message", "전체 " + list.size() + "개의 목록을 가져왔습니다.");
			resultMap.put("list", list);
		}
		return resultMap;
	}

	@GetMapping(value="searchMovie", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> searchMovie(QueryDTO query) {
		query.setSearchText(SecurityUtils.xss(query.getSearchText()));
		List<MovieDTO> list = movieService.findMoviesByQuery(query);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("message", query.getSearchText() + " 검색 결과가 없습니다.");
			resultMap.put("list", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("message", list.size() + "개의 검색 결과가 있습니다.");
			resultMap.put("list", list);
		}
		return resultMap;
	}

}
