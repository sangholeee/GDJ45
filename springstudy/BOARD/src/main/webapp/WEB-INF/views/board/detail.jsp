<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(document).ready(function(){
		$('#f').on('submit', (event)=>{
			alert('수정이 완료되었습니다.');
		})
	})

</script>
</head>
<body>

	
	<h1>게시글 수정화면</h1>
	
	<form id="f" action="${contextPath}/board/modify" method="post">
	
		<div>글번호 ${board.no}</div>
		<div>작성자 ${board.writer}</div>
		<div>작성IP ${board.ip}</div>
		<div>조회수 ${board.hit}</div>
		<div>작성일 ${board.created}</div>
		
		<div>
			제목 
			<input type="text" name="title" value="${board.title}">
			<br>
			<textarea name="content" rows="5" cols="30">${board.content}</textarea>
		</div>
		<div>
			<input type="hidden" name="no" value="${board.no}">
			<button>수정</button>
			<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
		</div>
		
	</form>
	

</body>
</html>