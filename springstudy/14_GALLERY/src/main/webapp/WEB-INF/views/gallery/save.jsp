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

	$(function(){
		fnFileCheck();
	})
	
	
	// 첨부파일 사전점검(확장자, 크기)
	// 선언 : var, let 같음
	// 단일 첨부 한 번 해보기 -> jspstudy 참고
	function fnFileCheck(){
		$('#files').on('change', function(){
			// 첨부 규칙
			// (.*) -> 파일명
			// \. -> .
			var regExt = /(.*)\.(jpg|png|gif)$/;
			var maxSize = 1024 * 1024 * 10; // 하나당 최대 크기
			
			// 첨부 가져오기
			var files = $(this)[0].files;
			
			// 각 첨부의 순회
			for(let i = 0; i < files.length; i++){
				// 확장자 체크
				if( regExt.test(files[i].name) == false ) {
					alert('이미지만 첨부할 수 있습니다.');
					$(this).val('');  // 첨부된 파일이 모두 없어짐
					return;
				}
				// 크기 체크
				if(files[i].size > maxSize){
					alert('10MB 이하의 파일만 첨부할 수 있습니다.');
					$(this).val('');
					return;
				}
			}
		})
	}
	

</script>
</head>
<body>

	<h1>작성화면</h1>	
	
	<!-- 
		파일 첨부 폼 : method="post" enctype="multipart/form-data"
		다중 첨부    : <input type="file" multiple="multiple">
		
	 -->
	
	<form id="f" action="${contextPath}/gallery/save" method="post" enctype="multipart/form-data">
	
		<input type="text" name="writer" placeholder="작성자"><br>
		<input type="text" name="title" placeholder="제목"><br>
		<input type="text" name="content" placeholder="내용"><br>
		첨부<br>
		<input type="file" name="files" id="files" multiple="multiple"><br>
		<!-- 단일첨부 <input type="file" name="files" id="files"><br> -->
		
		<button>작성완료</button>
	</form>	
	
</body>
</html>