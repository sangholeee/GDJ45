package com.goodee.movie.batch;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.domain.QueryDTO;
import com.goodee.movie.repository.MovieRepository;

@Component
public class MovieJob {

	@Autowired
	private MovieRepository movieRepository;
	
	@Scheduled(cron = "0 0/1 * * * ?")  // 1분마다 수행
	public void execute() throws Exception {
		
		QueryDTO query = new QueryDTO();
		query.setColumn("GENRE");
		query.setSearchText("코미디");
		List<MovieDTO> movies = movieRepository.selectMoviesByQuery(query);
		
		if(movies.size() == 0) {
			try (PrintWriter out = new PrintWriter(new FileWriter("C:\\exam\\MovieProject\\error.txt"))) {
				out.println("코미디 검색 결과가 없습니다.");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try (PrintWriter out = new PrintWriter(new FileWriter("C:\\exam\\MovieProject\\코미디.txt"))) {
				for (MovieDTO movie : movies) {
					out.println("제목 : " + movie.getTitle());
					out.println("장르 : " + movie.getGenre());
					out.println("개요 : " + movie.getDescription());
					out.println("평점 : " + movie.getStar());
				}
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
