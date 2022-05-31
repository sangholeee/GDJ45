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

	<c:if test="${member == null}">
		<form action="${contextPath}/free/login" method="post">
			<input type="text" name="id" placeholder="ID">
			<input type="password" name="pw" placeholder="Password">
			<button>로그인</button>
		</form>
	</c:if>
	
	<c:if test="${member != null}">
		${member.id} 님 반갑습니다.
	</c:if>
	
	<hr>
	
	<a href="${contextPath}/freeBoard/list">자유게시판</a>
	
</body>
</html>