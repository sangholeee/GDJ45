package com.goodee.naver3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface NaverService {

	public String login(HttpSession session);
	public void callback(HttpServletRequest request);
}
