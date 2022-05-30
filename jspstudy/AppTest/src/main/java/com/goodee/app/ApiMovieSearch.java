package com.goodee.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiMovieSearch {

	public static void main(String[] args) {
		
		String clientId = "WiiHW43ucrATJvCatiYq"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "rE10NhkWOR"; //애플리케이션 클라이언트 시크릿값"

      
        String text = JOptionPane.showInputDialog("영화 관련 검색어를 입력하세요.");
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        
        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text; 
        
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        try {
        	URL url = new URL(apiURL);
        	HttpURLConnection con = (HttpURLConnection)url.openConnection();
        	con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            // con.setRequestProperty("X-Naver-Client-Id", clientId);
            // con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            InputStream body = null;
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
            	body = con.getInputStream();
            } else {
            	body = con.getErrorStream();
            }
            
            InputStreamReader streamReader = new InputStreamReader(body);	// InputStream은 바이트기반이라 InputStreamReader에 넣어서 char기반으로 바꾸고,
            																// BufferedReader에 넣어서 속도 향상 시킴.
            StringBuilder responseBody = new StringBuilder();
            
            try (BufferedReader lineReader = new BufferedReader(streamReader)) {
                


                String line;
                while ((line = lineReader.readLine()) != null) {
                    responseBody.append(line);
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
            File dir = new File("C:\\storage");
            if(dir.exists() == false) {
            	dir.mkdirs();
            }
            File file = new File(dir, "movie.html");
            PrintWriter out = new PrintWriter(new FileWriter(file));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>");
            out.println("영화검색결과");
            out.println("</title>");
            out.println("<body>");

            
            JSONObject result = new JSONObject(responseBody.toString());
            // System.out.println(result.getString("lastBuildDate"));		// String인거 알고 빼는거
            // System.out.println( (String)result.get("lastBuildDate"));	// 나중에 String인걸 알아서 뒤늦게 형변환
            
            JSONArray items =  result.getJSONArray("items");	// items의 요소는 []에 묶여있다. JSONArray 배열보단 list에 가깝다.	{}는 JSONObject
            for(int i = 0; i < items.length(); i++) {
            	// items[i] (x)
            	// items.get(i); (o)
            	JSONObject item = items.getJSONObject(i);
            	String title = item.getString("title");
            	// System.out.println(title);
            	String link = item.getString("link");
            	String image = item.getString("image");
            	String userRating = item.getString("userRating");
            	out.println("<div>");
            	out.println("<a href=\"" + link + "\">" + title + "</a>");
            	out.println("<img src=\"" + image + "\" width=\"300px\"><br>");
            	out.println("<span>평점 " + userRating + "</span>");
            	out.println("</div>");
            }
            
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
            System.out.println("movie.html파일이 생성되었습니다.");
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        
        
        
        
	}

}
