package com.goodee.ex14.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;

public class MyFileUtils {

	// 파일명을 UUID로 변환
	public static String getUuid(String filename) {
		
		// regex -> 정규식 패턴
		// split은 정규식 패턴으로 쪼개는데 정규식에서 .은 모든 문자를 의미하기 때문에 모든 문자를 기준으로 쪼개려고 한다.
		// split -> "." 으로 하지 않고, "[.]" 또는 "\\."으로 해야 .을 구분한다.
		
		// 확장자
		String extension;
		if(filename.endsWith("tar.gz")) {
			extension = "tar.gz";
		} else if(filename.endsWith("tar.bz2")) {
			extension = "tar.bz2";
		} else {
			String[] arr = filename.split("\\.");
			extension = arr[arr.length - 1];
		}
		
		// 파일명(UUID) : 중복 없이 id를 만들기 위해 16진수 사용
		// 파일명 + 확장자
		return UUID.randomUUID().toString().replaceAll("\\-", "") + "." + extension;
	}
	
	// 오늘 경로
	// 2022\\5\\31
	public static String getTodayPath() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;           // January가 0에서 시작하기 때문에 + 1 해줘야 한다.
		int day = calendar.get(Calendar.DAY_OF_MONTH);          // 한 달 기준 
		String sep = Matcher.quoteReplacement(File.separator);  // File.separator : 구분자, \\
		return "C:" + sep + "upload" + sep + year + sep + month + sep + day;
	}

	// 어제 경로(Scheduling 에서 사용)
	public static String getYesterdayPath() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);                        // 하루 전 날로 변경
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;           // January가 0에서 시작하기 때문에 + 1 해줘야 한다.
		int day = calendar.get(Calendar.DAY_OF_MONTH);          // 한 달 기준 
		String sep = Matcher.quoteReplacement(File.separator);  // File.separator : 구분자, \\
		return "C:" + sep + "upload" + sep + year + sep + month + sep + day;
	}
	
	
	
}
