package com.goodee.ex13.domain;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardDTO {

	private Long rowNum;
	private Long freeBoardNo;
	private String writer;
	private String content;
	private String ip;
	private Date created;
	private Date modified;
	private Integer state;
	private Integer depth;
	private Integer groupNo;
	private Integer groupOrd;
	
}
