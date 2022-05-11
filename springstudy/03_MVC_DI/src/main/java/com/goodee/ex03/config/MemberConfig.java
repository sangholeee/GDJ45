package com.goodee.ex03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex03.domain.Member;

@Configuration
public class MemberConfig {

	// 자바로 bean을 만드는 경우에는
	// bean의 이름(id)이 qualifier 역할을 수행합니다.
	// @Qualifier 따로 추가 안해줘도 됨.
	// 그래도 controller에는 추가 해줘야 함.
	
	@Bean(name="member1")
	public Member member1() {
		
		Member member = new Member();
		member.setId("admin");
		member.setPw("123456");
		return member;
		
	}
	@Bean(name="member2")
	public Member member2() {
		
		Member member = new Member();
		member.setId("zico");
		member.setPw("아무노래");
		return member;
		
	}
}
