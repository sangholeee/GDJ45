package com.goodee.ex77.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {

	private Long no;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private Long hit;
	private Date created;
	private Date lastModified;
}
