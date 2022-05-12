<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<!-- Controller1에 요청하기 -->
	<a href="${contextPath}/detail1?name=민경태&age=45">정보전달1</a><br>
	<a href="${contextPath}/detail2?name=민경태&age=45">정보전달2</a><br>
	<a href="${contextPath}/detail3?name=민경태&age=45">정보전달3</a><br>
	<a href="${contextPath}/detail4?name=민경태&age=45">정보전달4</a><br>
	<a href="${contextPath}/detail5?name=민경태&age=45">정보전달5</a><br>
	

	<hr>

	<!-- Controller2에 요청하기, Board 도메인(Builder 패턴) 사용-->
	<a href="${contextPath}/add1?title=공지사항&hit=10">정보전달1</a><br>
	








</body>
</html>