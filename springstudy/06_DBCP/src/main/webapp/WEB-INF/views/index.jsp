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

	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

	<a href="${contextPath}/board/list">게시판으로 가기</a>
	
	<hr>
	
	<a href="${contextPath}/board/insert">게시글 등록하러 가기</a>


</body>
</html>