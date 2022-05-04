package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourStnInfoService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 인증키(Decoding)
		String serviceKey = "PyFSeQoS58x8tnyoPUI6MhKPV77hmv/Efd294nVMQxFhr02/zOfuRydM83N537vKHPX/DaPz/cr7SLpIL6ZMxA==";
		
		// 요청 Parameter
		String date = request.getParameter("date") + "00";
		String id = request.getParameter("id");
		
		// API 주소
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst");
			sb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sb.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
			sb.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
			sb.append("&CURRENT_DATE=").append(URLEncoder.encode(date, "UTF-8"));
			sb.append("&HOUR=").append(URLEncoder.encode("24", "UTF-8"));
			sb.append("&COURSE_ID=").append(URLEncoder.encode(id, "UTF-8"));
			sb.append("&dataType=").append(URLEncoder.encode("JSON", "UTF-8"));
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
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 연결된 API에서 json 받아오기
		// 응답 스트림 만들기(입력 스트림)
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
		
		// API로부터 받은 json을 tour.jsp로 보내기
		// tour.jsp로 응답하기
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(sb2.toString());  // ajax의 success로 넘어간다.
		out.flush();
		out.close();
		
	}

}
