package ex02_list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString


public class Song {

	private String title;
	private String genre;
	
	// removeSongByObject 메소드에서 사용
	@Override
	public boolean equals(Object obj) {       //Object obj로 전달되는 것이 new Song
		Song song = (Song)obj;
		return title.equals(song.title) && genre.equals(song.genre);
	}
}
