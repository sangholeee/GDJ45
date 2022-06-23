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
<script src="../resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="../resources/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/summernote-0.8.18-dist/summernote-lite.css" />
<script>

	$(function(){
		fnSummernote();
	}) 
	
	function fnSummernote(){
		$('#content').summernote({
			width: 600,
			height: 400,
			lang: 'ko-Kr',
			callbacks: {
				onImageUpload: function(files) {
					// 에디터에 첨부된 이미지
					var formData = new FormData();
					formData.append('file', files[0]);     // file이 파라미터임!
					// 이미지 저장을 위한 ajax
					$.ajax({
						url: '${contextPath}/bbs/uploadSummernoteImage',
						type: 'POST',
						data: formData,
						contentType: false,
						processData: false,
						success: function(obj){
							$('#content').summernote('insertImage', obj.src);
						}
					})
				}
			}
		})
	}

</script>
</head>
<body>

	<h1>게시글작성화면</h1>
	
	<form id="f" action="${contextPath}/bbs/add" method="post">
		<input type="text" name="writer" placeholder="작성자"><br>
		<input type="text" name="title" placeholder="제목"><br>
		<textarea name="content" id="content"></textarea><br><br>
		<button>작성완료</button>
	</form>

</body>
</html>