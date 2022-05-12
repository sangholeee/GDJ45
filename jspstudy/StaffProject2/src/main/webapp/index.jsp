<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		fnInsertStaff();
		fnQueryStaff();
		fnSelectStaffList();
	});
	function fnInit(){
		$('#sno').val('');
		$('#name').val('');
		$('#dept').val('');
	}
	function fnInsertStaff(){
		$('#btn_insert').click(function(){
			var regSNO = /^[0-9]{5}$/;
			if (!regSNO.test($('#sno').val())) {
				alert("사원번호는 5자리 숫자입니다.");
				return;
			}
			var regDEPT = /^[가-힣]{3,5}$/;
			if (!regDEPT.test($('#dept').val())) {
				alert("부서는 3~5자리 한글입니다.");
				return;
			} 
			$.ajax({
				url: 'add.json',
				type: 'post',
				data: $('#form_insert').serialize(),
				dataType: 'json',
				success: function(obj) {
					if (obj.res > 0) {
						alert('사원 등록이 성공했습니다.');
						fnSelectStaffList();
						fnInit();
					}
				},
				error: function(jqXHR) {
					if (jqXHR.status == 1001 || jqXHR.status == 1002 ) {
						alert(jqXHR.responseText);
					}
				}
			});
		});
	}
	function fnQueryStaff(){
		$('#btn_query').click(function(){
			$.ajax({
				url: 'query.json',
				type: 'get',
				data: 'query=' + $('#query').val(),
				dataType: 'json',
				success: function(obj) {
					$('#staff_list').empty();
					$('<tr>')
					.append( $('<td>').text(obj.sno) )
					.append( $('<td>').text(obj.name) )
					.append( $('<td>').text(obj.dept) )
					.append( $('<td>').text(obj.salary) )
					.appendTo('#staff_list');
				}
			});
		});
	}
	function fnSelectStaffList(){
		$.ajax({
			url: 'list.json',
			type: 'get',
			dataType: 'json',
			success: function(obj) {
				$('#staff_list').empty();
				fnStaffTable(obj);
			}
		});
	}
	function fnStaffTable(list){
		$.each(list, function(i, staff){
			$('<tr>')
			.append( $('<td>').text(staff.sno) )
			.append( $('<td>').text(staff.name) )
			.append( $('<td>').text(staff.dept) )
			.append( $('<td>').text(staff.salary) )
			.appendTo('#staff_list');
		});
	}
</script>
</head>
<body>
	<h3>사원등록</h3>
	<form id="form_insert">
		<input type="text" name="sno" id="sno" placeholder="사원번호">
		<input type="text" name="name" id="name" placeholder="사원명">
		<input type="text" name="dept" id="dept" placeholder="부서명">
		<input type="button" value="등록" id="btn_insert">
	</form>
	<hr>
	<h3>사원조회</h3>
	<form id="form_query">
		<input type="text" name="query" id="query" placeholder="사원번호입력">
		<input type="button" value="조회" id="btn_query">
		<input type="button" value="전체" onclick="fnSelectStaffList()">
	</form>
	<hr>
	<h3>사원목록</h3>
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서명</td>
				<td>연봉</td>
			</tr>
		</thead>
		<tbody id="staff_list"></tbody>
	</table>
</body>
</html>