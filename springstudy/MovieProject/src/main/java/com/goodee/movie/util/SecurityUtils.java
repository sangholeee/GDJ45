package com.goodee.movie.util;

public class SecurityUtils {
	
	public static String xss(String str) {
		str = str.replaceAll("[<]", "&lt;");
		str = str.replaceAll("[>]", "&gt;");
		str = str.replaceAll("[&]", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("\'", "&#x27");
		str = str.replaceAll("[(]", "&#40;");
		str = str.replaceAll("[)]", "&#41;");
		str = str.replaceAll("[/]", "&#x2F;");		
		return str;
	}

}
