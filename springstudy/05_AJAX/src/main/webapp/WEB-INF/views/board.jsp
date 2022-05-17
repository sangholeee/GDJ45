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

	$(document).ready(function() {
		$('#btn1').on('click', function(){
			fnAjax1();
		})
		$('#btn2').on('click', function(){
			fnAjax2();
		})
		$('#btn3').on('click', function(){
			fnAjax3();
		})
		$('#btn4').on('click', function(){
			fnAjax4();
		})
	})
	
	
	// 요청 데이터 : 파라미터
	// 응답 데이터 : JSON
	function fnAjax1(){
		
		$('#result').empty();
		
		$.ajax({
		
			url: '${contextPath}/board/detail1',
			type: 'get',
			data: 'title=' + $('#title').val() + '&hit=' + $('#hit').val(),
			
			dataType: 'json',
			success: function(obj){
				var tr = '<tr>';
				tr += '<td>제목 : ' + obj.title + '&nbsp&nbsp</td>';
				tr += '<td>조회수 : ' + obj.hit + '</td>';
				$('#result').append(tr);
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
		})
	}
	
	
	// 요청 데이터 : 파라미터
	// 응답 데이터 : JSON
	function fnAjax2(){
		
		$('#result').empty();
		
		$.ajax({
			
			url: '${contextPath}/board/detail2',
			type: 'get',
			data: $('#f').serialize(),
			
			dataType: 'json',
			success: function(obj){
				var tr = '<tr>';
				tr += '<td>제목 : ' + obj.title + '&nbsp&nbsp</td>';
				tr += '<td>조회수 : ' + obj.hit + '</td>';
				$('#result').append(tr);
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
			
		})
		
	}
	
	
	// 요청 데이터 : JSON
	// 응답 데이터 : JSON
	function fnAjax3(){
		
		$('#result').empty();
		
		$.ajax({
		
			url: '${contextPath}/board/detail3',
			type: 'post',
			data: JSON.stringify({
				title: $('#title').val(),
				hit: $('#hit').val()
			}),
			contentType: 'application/json',
			
			dataType: 'json',
			success: function(obj){
				var tr = '<tr>';
				tr += '<td>제목 : ' + obj.title + '&nbsp&nbsp</td>';
				tr += '<td>조회수 : ' + obj.hit + '</td>';
				$('#result').append(tr);
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.status + ' : ' + jqXHR.responseText);
			}
			
		})
		
	}
	
	
	// 요청 데이터 : JSON
	// 응답 데이터 : JSON
	function fnAjax4(){
		
		$('#result').empty();
		
		$.ajax({
			
			url: '${contextPath}/board/detail4',
			type: 'post',
			data: JSON.stringify({
				title: $('#title').val(),
				hit: $('#hit').val()
			}),
			contentType: 'application/json',
			
			dataType: 'json',
			success: function(obj){
				var tr = '<tr>';
				tr += '<td>제목 : ' + obj.title + '&nbsp&nbsp</td>';
				tr += '<td>조회수 : ' + obj.hit + '</td>';
				$('#result').append(tr);
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
		<input type="text" name="title" id="title"><br>
		<input type="text" name="hit" id="hit"><br>
		<input type="button" value="전송1" id="btn1">
		<input type="button" value="전송2" id="btn2">
		<input type="button" value="전송3" id="btn3">
		<input type="button" value="전송4" id="btn4">
	</form>
	
	<hr>
	
	<div id="result">
	</div>

</body>
</html>