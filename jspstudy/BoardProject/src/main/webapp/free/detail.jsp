<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>자유게시판 게시글 상세보기화면</h1>
	<form action="/BoardProject/modify.do" method="post">
	
	<div>글번호 ${board.freeNo}</div>
	<div>작성자 ${board.writer}</div>
	<div>작성IP ${board.ip}</div>
	<div>조회수 ${board.hit}</div>
	<div>
		제목 
		<input type="text" name="title" value="${board.title}">
	</div>
	<div>
		<textarea name="content" rows="5" cols="30">${board.content}</textarea>
	</div>
	
	<div>
		<input type="hidden" name="freeNo" value="${board.freeNo}">
		<input type="submit" value="수정">
		<input type="button" value="목록" onclick="location.href='/BoardProject/list.do'">
	</div>
	</form>
</body>
</html>