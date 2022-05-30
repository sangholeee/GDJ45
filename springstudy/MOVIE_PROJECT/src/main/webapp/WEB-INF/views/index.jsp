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

	$(function(){
		alert('전체 ${count}개의 목록을 가져왔습니다.');
		fnList();
		
		$('#btnList').on('click', function(){
			fnList();
		})
		$('#btnSearch').on('click', function(){
			fnSearch();
		})
		
	})
	
	function fnList(){
		
		$.ajax({
			url: '${contextPath}/searchAllMovies',
			type: 'get',
			dataType: 'json',
			success: function(result){
				$('#movies').empty();
				$.each(result, function(i, movie){
					var tr = '<tr>';
					tr += '<td>' + movie.title + '</td>';
					tr += '<td>' + movie.genre + '</td>';
					tr += '<td>' + movie.description + '</td>';
					tr += '<td>' + movie.star + '</td></tr>';
					$('#movies').append(tr);
						
				})
			}
			
		})
	}
	
	function fnSearch(){
		
		
		$.ajax({
			url: '${contextPath}/searchMovie',
			type: 'get',
			data: 'column=' + $('#column').val() + '&searchText=' + $('#searchText').val(),
			dataType: 'json',
			success: function(result){
				$('#movies').empty();
				alert(result.length + '개의 검색 결과가 있습니다.');
				$.each(result, function(i, movie){
					var tr = '<tr>';
					tr += '<td>' + movie.title + '</td>';
					tr += '<td>' + movie.genre + '</td>';
					tr += '<td>' + movie.description + '</td>';
					tr += '<td>' + movie.star + '</td></tr>';
					$('#movies').append(tr);
						
				})
			},
			error: function(jqXHR){
				alert($('#searchText').val() + ' 검색결과가 없습니다.');
			}
			
		})
	}
	

</script>
</head>
<body>

	<form id="f" method="get">
		<select name="column" id="column">
			<option value="TITLE">제목</option>
			<option value="GENRE">장르</option>
			<option value="DESCRIPTION">개요</option>
		</select>
		<input type="text" name="searchText" id="searchText">
		<input type="button" value="검색" id="btnSearch">
		<input type="button" value="초기화" id="btnList">
	</form>
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>제목</td>
				<td>장르</td>
				<td>개요</td>
				<td>평점</td>
			</tr>
		</thead>
		<tbody id="movies">
		
		</tbody>
	</table>


</body>
</html>