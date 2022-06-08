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

		<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<h3>로그인</h3>
	
	<form id="f" action="${contextPath}/member/login" method="post">
		아이디<br>
		<input type="text" name="id" id="id"><br><br>
		
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
		
		<button>로그인</button><br><br>
		
		<label for="rememberId"><input type="checkbox" id="rememberId">아이디 저장</label>
		<label for="keepLogin"><input type="checkbox" name="keepLogin" id="keepLogin">로그인 유지</label>
		
	</form>
	
	<div>
		<a href="${contextPath}/member/findIdPage">아이디 찾기</a>
		<a href="${contextPath}/member/findPwPage">비밀번호 찾기</a>
		<a href="${contextPath}/member/agreePage">회원가입</a>
	</div>
	
	
</body>
</html>