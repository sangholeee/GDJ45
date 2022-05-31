<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		fnSearchAll();
		fnSearch();
		fnInit();
	});
	function fnShowList(movies, status) {
		$('#list').empty();
		if (status == 200) {
			$.each(movies, function(idx, movie) {
				$('<tr>')
				.append($('<td>').html(movie.title))
				.append($('<td>').html(movie.genre))
				.append($('<td>').html(movie.description))
				.append($('<td>').html(movie.star))
				.appendTo('#list');
			});
		} else if (status == 500) {
			$('<tr>')
			.append($('<td colspan="4">').html('검색 결과가 없습니다.'))
			.appendTo('#list');
		}
	}
	function fnSearchAll() {
		$.ajax({
			url: 'searchAllMovies',
			type: 'get',
			dataType: 'json',
			success: function(result) {
				alert(result.message);
				fnShowList(result.list, result.status);						
			}
		});
	}
	function fnSearch() {
		$('#btnSearch').click(function() {
			$('#list').empty();
			$.ajax({
				url: 'searchMovie',
				type: 'get',					
				data: 'column=' + $('#column').val() + '&searchText=' + $('#searchText').val(),
				dataType: 'json',
				success: function(result) {
					alert(result.message);
					fnShowList(result.list, result.status);
				}
			});
		});
	}
	function fnInit() {
		$('#btnInit').click(function(){
			fnSearchAll();
		});
	}
</script>
</head>
<body>

	<form method="get">
		
		<select id="column" name="column">
			<option value="TITLE">제목</option>
			<option value="GENRE">장르</option>
			<option value="DESCRIPTION">내용</option>
		</select>
		<input type="text" id="searchText" name="searchText">
		<input type="button" id="btnSearch" value="검색">
		<input type="button" id="btnInit" value="초기화">
		
		<br><hr><br>
		
		<table border="1">
			<thead>
				<tr>
					<td>제목</td>
					<td>장르</td>
					<td>내용</td>
					<td>평점</td>
				</tr>
			</thead>
			<tbody id="list"></tbody>
		</table>
		
	</form>

</body>
</html>