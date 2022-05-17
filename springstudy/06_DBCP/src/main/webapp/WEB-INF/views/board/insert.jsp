<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시글작성화면</h1>
	<form action="${contextPath}/board/insert" method="post">
		작성자 <input type="text" name="writer"><br>
		제목 <input type="text" name="title"><br>
		내용<br>
		<textarea name="content" rows="5" cols="30"></textarea>
		<br><br>
		<button>작성</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
	</form>

</body>
</html>