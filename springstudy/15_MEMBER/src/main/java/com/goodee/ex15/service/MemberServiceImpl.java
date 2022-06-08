package com.goodee.ex15.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex15.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Map<String, Object> idCheck(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.selectMemberById(id));
		return map;
	}
	
	@Override
	public Map<String, Object> emailCheck(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.selectMemberByEmail(email));
		return map;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		
		// 인증코드
		String authCode = "111111";
		
		// 필수속성
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");     // 구글 메일로 보냅니다.
		props.put("mail.smtp.port", "587");                // 구글 메일 보내는 포트.
		props.put("mail.smtp.auth", "true");               // 인증되었다.
		props.put("mail.smtp.starttls.enable", "true");    // TLS 허용한다.
		
		// 메일을 보내는 사용자 정보
		final String USERNAME = "각자구글아이디";   
		final String PASSWORD = "각자구글비밀번호";
		
		// 사용자 정보 javax.mail.Session에서 저장
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		
		/*
			이메일 보내기
			1. 사용자 정보는 구글 메일만 가능합니다.
			2. 가급적 구글 부계정을 만들어서 사용하세요.
			3. 구글에서 '보안 수준이 낮은 앱 허용' 을 해야합니다.
			   https://support.google.com/accounts/answer/6010255 막힘
		 */
		
		// 이메일 전송하기
		try {
			
			Message message = new MimeMessage(session);
			
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress(USERNAME, "인증코드관리자"));               // 이메일 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));     // 이메일 받는 사람(To : 개인, CC : 다수, BCC : 받는사람은 모르는 다수)  
			message.setSubject("인증 요청 메일입니다.");
			message.setText("인증번호는 " + authCode + "입니다.");
			
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("authCode", authCode);
		
		return map;
	}	
}
