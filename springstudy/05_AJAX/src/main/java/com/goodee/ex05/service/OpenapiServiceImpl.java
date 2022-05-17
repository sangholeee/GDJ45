package com.goodee.ex05.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class OpenapiServiceImpl implements OpenapiService {

	@Override
	public Map<String, Object> dailyBoxOffice(String targetDt) {
		
		String serviceKey = "bff0fcfdd438435ac5f9356cb035e0fc";
		
		StringBuilder sb = new StringBuilder();
	
		try {
			sb.append("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json");
			sb.append("?key=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sb.append("&targetDt=").append(URLEncoder.encode(targetDt, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = sb.toString();
		
		// API 주소 연결
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
		} catch (MalformedURLException e) {
			e.printStackTrace();   // apiURL이 잘못되었다.
		} catch (IOException e) {
			e.printStackTrace();   // API 연결이 실패하였다.
		}
		
		BufferedReader br = null;
		StringBuilder sb2 = new StringBuilder();
		try {
			// 응답 성공하면 정상 스트림, 실패하면 에러 스트림
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			// json 읽어 들이기
			String line = null;
			while((line = br.readLine()) != null) {
				sb2.append(line);
			}
			// 스트림, 연결 종료
			if(br != null) {
				br.close();
			}
			if(con != null) {
				con.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("res", sb2.toString());
		
		return res;
		
	}

}
