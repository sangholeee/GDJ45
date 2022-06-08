package com.goodee.ex15.service;

import java.util.Map;

public interface MemberService {

	public Map<String, Object> idCheck(String id);
	public Map<String, Object> emailCheck(String email);
	public Map<String, Object> sendAuthCode(String email);
	
}
