package com.goodee.ex08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data       // Getter, Setter, ToString 다 넣어준다.
public class BookDTO {
	
	private Long book_no;
	private String title;
	private String author;
	private Integer price;
	private String pubdate;
	private String regdate;

}
