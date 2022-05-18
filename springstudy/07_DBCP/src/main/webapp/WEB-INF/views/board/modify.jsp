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
<link rel="stylesheet" href="../resources/css/summernote-0.8.18-dist/summernote-lite.css">
<script src="../resources/js/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="../resources/js/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<script>

	// 페이지 로드 이벤트
	$(document).ready(function() {
		
		// 폼의 서브밋 이벤트
		$('#f').on('submit', (event)=>{
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				alert('제목을');
				alert('입력');
				alert('하십시오');
				alert('!');
				event.preventDefault();
			}
		})
		
		// summernote
		$('#content').summernote({
			width: 1000,
			height: 300,
			lang: 'ko-Kr',
			placeholder: '내용'
		})
		
	})


</script>
</head>
<body>

	<h1>게시글 수정화면</h1>
	
	<form id="f" action="${contextPath}/board/modify" method="post">
	
		<div>글번호 ${board.board_no}</div>
		<div>작성자 ${board.writer}</div>
		<div>작성일 ${board.created}</div>
		<div>최종수정일 ${board.lastModified}</div>
		
		<div>
			제목 
			<input type="text" name="title" id="title" value="${board.title}">
			<br>
			<textarea name="content" id="content">${board.content}</textarea>
		</div>
		<div>
			<input type="hidden" name="board_no" value="${board.board_no}">
			<button>수정완료</button>
			<input type="reset" value="다시작성">
			<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
		</div>
		
	</form>

</body>
</html>