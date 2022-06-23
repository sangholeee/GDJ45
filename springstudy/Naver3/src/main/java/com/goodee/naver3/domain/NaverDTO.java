package com.goodee.naver3.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverDTO {

	private Long naverNo;
	private String id;
	private String name;
	private String email;
	private String gender;
	private String birth;
	private String mobile;
	
}
