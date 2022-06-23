package com.goodee.kakao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoDTO {

	private String id;
	private String birthday;
	private String gender;
	private String email;
	private String name;
	
	
}
