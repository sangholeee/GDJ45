<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery library -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<!-- jquery ui library -->
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js" integrity="sha256-6XMVI0zB8cRzfZjqKcD01PBsAy3FlDASrlC8SxCpInY=" crossorigin="anonymous"></script>
<!-- jquery ui theme css -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script>


	// 페이지 로드 이벤트
	$(document).ready(function(){
		$('#date').datepicker({
			showOn: 'focus',
			dateFormat: 'yymmdd'
		});
		
		fntourStnInfo();
	})
	
	
	// 함수
	function fntourStnInfo(){
		$('#btnSearch').on('click', function(){
			$.ajax({
				url: '/OPENAPI/tourStnInfo.do',
				data: $('#formSearch').serialize(),
				type: 'get',
				dataType: 'json',
				success: function(responseText){
					console.log(responseText);
					var items = responseText.response.body.items.item;
					$('#items').empty();
					$.each(items, function(i, item){
						var tr = '<tr>';
						tr += '<td>' + item.courseAreaName + '</td>';
						tr += '<td>' + item.courseName + '</td>';
						tr += '<td>' + item.tm + '</td>';
						tr += '<td>' + item.pop + '</td>';
						tr += '<td>' + item.rhm + '</td>';
						tr += '<td>' + item.th3 + '</td>';
						tr += '<td>' + item.ws + '</td>';
						$('#items').append(tr);
						
					})
				}
			})
		});
		
	}
</script>
</head>
<body>

	<div>
		<form id="formSearch">
			<label for="date">
				조회시작시간
				<input type="text" name="date" id="date">	
			</label>
			<label for="id">
				코스ID
				<input type="text" name="id" id="id">	
			</label>
			<input type="button" value="검색" id="btnSearch">
		</form>
	</div>
	<hr>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>지역이름</td>
					<td>코스명</td>
					<td>예보시각</td>
					<td>강수확률</td>
					<td>습도</td>
					<td>일3시간 기온</td>
					<td>풍속</td>
				</tr>
			</thead>
			<tbody id="items"></tbody>
		</table>
 	</div>
	

</body>
</html>