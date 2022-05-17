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
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js" integrity="sha256-6XMVI0zB8cRzfZjqKcD01PBsAy3FlDASrlC8SxCpInY=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">

<script>

	//페이지 로드 이벤트
	$(document).ready(function(){
		$('#targetDt').datepicker({
			showOn: 'focus',
			dateFormat: 'yymmdd'       // 실제 생성 날짜는 yyyymmdd
		});
	
		$('#btn').on('click', function(){
			fnAjax();
		})
	})
	
	function fnAjax(){
		
			
		$('#result').empty();
		$.ajax({
				
			url: '${contextPath}/openapi/dailyBoxOffice',
			type: 'get',
			data: 'targetDt=' + $('#targetDt').val(),
				
			dataType: 'json',
			success: function(res){
				console.log(res);
				$.each(res.boxOfficeResult.dailyBoxOfficeList, function(i, list){
					var tr = '<tr>';
					tr += '<td>' + list.movieNm + '</td>';
					tr += '<td>' + list.rank + '</td>';
					tr += '<td>' + list.openDt + '</td>';
					tr += '<td>' + list.audiCnt + '</td>';
					tr += '<td>' + list.audiAcc + '</td>';
					tr += '<td>' + list.rankOldAndNew + '</td>';
					$('#result').append(tr);
					
				})
			}
		})
		
	}

</script>
</head>
<body>
	
	순위(+1/-1) 조회일자 영화이름 개봉일 일일관객수 누적관객수 비고(신규의 경우 신규)
	
	<div>
			<label for="targetDt">
				조회날짜
				<input type="text" name="targetDt" id="targetDt">	
			</label>
			<input type="button" value="검색" id="btn">
	</div>
	<hr>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>영화이름</td>
					<td>순위</td>
					<td>개봉일</td>
					<td>일일관객수</td>
					<td>누적관객수</td>
					<td>비고</td>
				</tr>
			</thead>
			<tbody id="result"></tbody>
		</table>
 	</div>

</body>
</html>