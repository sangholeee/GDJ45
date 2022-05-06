<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	// 페이지 로드 이벤트
	$(document).ready(function(){
		fnCovid19InfState();
	})
	
	// 함수
	function fnCovid19InfState(){
		$('#btn').on('click', function(){
			$.ajax({
				url: '/OPENAPI/covid19InfState.do',
				type: 'get',
				dataType: 'xml',
				success: function(responseText){
					console.log(responseText);
					
					$('#infState').empty();
					$.each($(responseText).find('item'), function(i, item) {
						var tr = '<tr>';
						tr += '<td>' + $(item).find('stateDt').text() + '</td>';
						tr += '<td>' + $(item).find('stateTime').text() + '</td>';
						tr += '<td>' + $(item).find('decideCnt').text() + '</td>';
						tr += '<td>' + $(item).find('deathCnt').text() + '</td>';
						$('#infState').append(tr);
					})
					
				}
			})
		})	
	}
</script>
</head>
<body>

	<input type="button" value="코로나19 감염 현황" id="btn">

	<hr>

	<table border="1">
		<thead>
			<tr>
				<td>기준일</td>
				<td>기준시간</td>
				<td>확진자 수</td>
				<td>사망자 수</td>
			</tr>
		</thead>
		<tbody id="infState"></tbody>
	</table>


</body>
</html>