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

	$(document).ready(()=> {
		
		// 삭제
		$('#btnRemove').on('click', ()=>{
			if(confirm('삭제할까요?')){
				location.href='${contextPath}/board/remove?board_no=${board.board_no}';
			}
		})
	})

</script>
</head>
<body>

	<h1>게시글상세보기</h1>
	<div>글번호 :  ${board.board_no}</div>
	<div>작성일 :  ${board.created}</div>
	<div>최종수정일 :  ${board.lastModified}</div>
	<div>작성자 :  ${board.writer}</div>
	<div>제목 :  ${board.title}</div>
	<div><pre>${board.content}</pre></div>
	
	
	<form action="${contextPath}/board/modifyPage" method="post">
		<input type="submit" value="수정">
		<input type="hidden" name="board_no" value="${board.board_no}">
		<input type="hidden" name="title" value="${board.title}">
		<input type="hidden" name="content" value="${board.content}">
		<input type="button" value="삭제" id="btnRemove">
		<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
	</form>

</body>
</html>