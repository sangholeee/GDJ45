package com.goodee.ex15.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {

	private Long memberNo;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	private Integer agreeState;
	private Date signIn;
	private Date pwModified;
	private Date infoModified;
	private String sessionId;
	private Date sessionLimit;
	
}
