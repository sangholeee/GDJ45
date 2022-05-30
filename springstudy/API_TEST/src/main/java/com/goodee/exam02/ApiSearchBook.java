package com.goodee.exam02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiSearchBook {

	
	
	public static void main(String[] args) {
		
		Date time = new Date();
		String error = "";
		String formatNow = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		String clientId = "RntwodBI35vlHeOUL9RI"; 
        String clientSecret = "M0v2h2B5RN"; 

      
        String text = JOptionPane.showInputDialog("책 관련 검색어를 입력하세요.");
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            error = e.getMessage();
    		formatNow = formatter.format(time);
        }
        
        try {
        	
        	String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text; 
        	
        	Map<String, String> requestHeaders = new HashMap<>();
        	requestHeaders.put("X-Naver-Client-Id", clientId);
        	requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        	
        	URL url = new URL(apiURL);
        	HttpURLConnection con = (HttpURLConnection)url.openConnection();
        	con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            
            InputStream body = null;
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
            	body = con.getInputStream();
            } else {
            	body = con.getErrorStream();
            }
            
            InputStreamReader streamReader = new InputStreamReader(body);	
            																
            StringBuilder responseBody = new StringBuilder();
            
            try (BufferedReader lineReader = new BufferedReader(streamReader)) {
                
                String line;
                while ((line = lineReader.readLine()) != null) {
                    responseBody.append(line);
                }
            } catch (Exception e) {
            	e.getMessage();
        		formatNow = formatter.format(time);
            }
            
            File dir = new File("C:\\download");
            if(dir.exists() == false) {
            	dir.mkdirs();
            }
            String text1 = URLDecoder.decode(text, "UTF-8");
            File file = new File(dir, text1 + ".html");
            PrintWriter out = new PrintWriter(new FileWriter(file));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>");
            out.println("책검색결과");
            out.println("</title>");
            out.println("<body>");

            
            JSONObject result = new JSONObject(responseBody.toString());
            
            JSONArray items =  result.getJSONArray("items");	
            for(int i = 0; i < items.length(); i++) {
            	JSONObject item = items.getJSONObject(i);
            	String title = item.getString("title");
            	String link = item.getString("link");
            	String image = item.getString("image");
            	out.println("<div>");
            	out.println("<a href=\"" + link + "\">" + title + "</a><br>");
            	out.println("<img src=\"" + image + "\" width=\"100px\"><br>");
            	out.println("</div>");
            	out.println("<hr>");
            }
            
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
            System.out.println(text1 + ".html파일이 생성되었습니다.");
            
        } catch (MalformedURLException e) {
            error = e.getMessage();
         	formatNow = formatter.format(time);
        } catch (IOException e) {
            error = e.getMessage();
         	formatNow = formatter.format(time);
		} catch (NullPointerException e) {
		    error = e.getMessage();
	        formatNow = formatter.format(time);
		} catch(Exception e) {
        	error = e.getMessage();
        	formatNow = formatter.format(time);
        
        }
        try {
        	 File dir2 = new File("C:\\download//log");
             if(dir2.exists() == false) {
             	dir2.mkdirs();
             }
             File file2 = new File(dir2, "error_log.txt");
             PrintWriter out2 = new PrintWriter(new FileWriter(file2));
             out2.print("MESSAGE -> " + error);
             out2.print("      TIME -> " + formatNow);
             out2.flush();
             out2.close();
             System.out.println("error_log.txt파일이 생성되었습니다.");

        } catch (Exception e) {
        	e.printStackTrace();
		}
        
	}

}
