package com.goodee.naver3.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.naver3.domain.NaverDTO;
import com.goodee.naver3.mapper.NaverMapper;

@Service
public class NaverServiceImpl implements NaverService {

	@Autowired
	private NaverMapper naverMapper;
	
	@Override
	public String login(HttpSession session) {

		String clientId = "XYULZHj0e4wadrMeNhvI";//애플리케이션 클라이언트 아이디값";
		String redirectURI = null;
		try {
			redirectURI = URLEncoder.encode("http://localhost:9090/naver3/callback", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    System.out.println(apiURL);
	    session.setAttribute("state", state);
		
		return apiURL;
	}
	
	@Override
	public void callback(HttpServletRequest request) {
		
		String clientId = "XYULZHj0e4wadrMeNhvI";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "4gVLsa0no9";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = null;
		try {
			redirectURI = URLEncoder.encode("http://localhost:9090/naver3/callback", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	    
		String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;

	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.println("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      JSONObject obj = new JSONObject(res.toString());
	      
		  String access_token = obj.getString("access_token"); // 네이버 로그인 접근 토큰;
		  // String refresh_token = obj.getString("refresh_token");
	      
		  String header = "Bearer " + access_token; // Bearer 다음에 공백 추가


	      String apiURL2 = "https://openapi.naver.com/v1/nid/me";


	      Map<String, String> requestHeaders = new HashMap<>();
	      requestHeaders.put("Authorization", header);
	      String responseBody = get(apiURL2,requestHeaders);


	      System.out.println(responseBody);
	      
	      JSONObject obj2 = new JSONObject(responseBody.toString());
	      System.out.println(obj2.get("response"));
	      
	      JSONObject obj3 = obj2.getJSONObject("response");
	      String id = obj3.getString("id");
	      String name = obj3.getString("name");
	      String email = obj3.getString("email");
	      String gender = obj3.getString("gender");
	      String birth = obj3.getString("birthyear") + "-" + obj3.getString("birthday");
	      String mobile = obj3.getString("mobile");
	      
	      System.out.println(birth);
		  NaverDTO naver = NaverDTO.builder()
				  .id(id)
				  .name(name)
				  .email(email)
				  .gender(gender)
				  .birth(birth)
				  .mobile(mobile)
				  .build();
		  
		  System.out.println(naver);
		  
		  naverMapper.insertNaver(naver);
		  
		  HttpSession session = request.getSession();
	      session.setAttribute("loginMember", naver);
		  
	      
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	}
	
	
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
