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
		$('#btn1').on('click', fnAjax1);
		$('#btn2').on('click', fnAjax2);
		$('#btn3').on('click', fnAjax3);
	})
	
	// 함수
	
	function fnAjax1(){
		
		$.ajax({
			/* 요청 */
			url: '${contextPath}/reservation/detail1',
			type: 'get',
			data: 'no=' + $('#no').val(),
			
			/* 응답 */
			dataType: 'json',
			success: function(reservation){
				$('#result').empty();
				$('<ul>')
				.append($('<li>').text(reservation.no))
				.append($('<li>').text(reservation.name))
				.appendTo('#result');
			},
			error: function(jqXHR){
				if(jqXHR.status == 400){             // HttpStatus.BAD_REQUEST == 400
					alert('잘못된 요청입니다.');
				}
				$('#result').empty();
				$('#result').text(jqXHR.responseText);
			}
			
			
		})
		
	}   // fnAjax1
	
	
	function fnAjax2(){
		
		$.ajax({
			url: '${contextPath}/reservation/detail2',
			type: 'get',
			data: 'no=' + $('#no').val(),
			
			dataType: 'json',
			success: function(reservation){
				$('#result').empty();
				$('<ul>')
				.append($('<li>').text(reservation.no))
				.append($('<li>').text(reservation.name))
				.appendTo('#result');
			},
			error: function(jqXHR){
				if(jqXHR.status == 400) {
					alert('예약번호는 숫자입니다.');
				}
				$('#result').empty();
				$('#result').text(jqXHR.responseText);
			}
		})
		
	}   // fnAjax2
	
	
	function fnAjax3(){
		
	}   // fnAjax3

</script>
</head>
<body>

	<input type="text" name="no" id="no" placeholder="예약번호">
	<input type="button" value="조회1" id="btn1">
	<input type="button" value="조회2" id="btn2">
	<input type="button" value="조회3" id="btn3">

	<hr>
	
	<div id="result"></div>


</body>
</html>