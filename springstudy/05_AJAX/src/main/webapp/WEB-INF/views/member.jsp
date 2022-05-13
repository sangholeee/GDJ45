<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.0.js"></script>
<script>
	
	// 페이지 로드 이벤트
	$(document).ready(function(){
		$('#btn1').on('click', function(){
			fnAjax1();    // btn1을 클릭하면 fnAjax1() 함수를 실행하시오.
		})
	})
	
	// 호이스팅 -> 자바 스크립트는 선언부를 먼저 읽어보고 나머지를 읽는다.
	
	// 함수
	function fnAjax1(){
		
		$('#result').empty();
		
		$.ajax({
			
			/* 요청에 관한 프로퍼티 : url, type, data, contentType */
			url: '${contextPath}/member/detail1',                      // 요청 URL(매핑), MemberController에서 매핑을 찾자.
			type: 'get',                                               // 요청 메소드(get, post 등)
			data: 'id=' + $('#id').val() + '&pw=' + $('#pw').val(),    // MemberController로 보내는 파라미터 id와 pw

			/* 응답 데이터에 관한 프로퍼티 : dataType, success, error */
			dataType: 'text',         // 응답 데이터 타입 text
			success: function(res){   // 응답 데이터는 res에 전달됨
				$('#result').text(res);
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
		
	}
	
</script>
</head>
<body>

	<form id="f">
		<input type="text" name="id" id="id" placeholder="ID"><br>
		<input type="text" name="pw" id="pw" placeholder="PassWord"><br>
		<input type="button" value="전송1" id="btn1">
	</form>
	
	<hr>
	
	<div id="result"></div>

</body>
</html>