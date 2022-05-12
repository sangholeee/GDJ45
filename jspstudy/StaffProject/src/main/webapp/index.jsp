<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	$(document).ready(function(){
		fnList();
		fnAdd();
		fnDetail();
		fnAll();
		
		var sno = $('#sno');
		var regsno = /^[0-9]{5}$/;
		sno.on('blur', function(event) {
			if(regsno.test($(this).val())==false){
				alert('사원번호는 5자리 숫자입니다.');
			}
		});
		var dept = $('#dept');
		var regdept = /^[가-힣]{3,5}$/;
		dept.on('blur', function(event) {
			if(regdept.test($(this).val())==false){
				alert('부서는 3~5자리 한글입니다.');
			}
		});
		
	})
	
	function fnList(){
		$.ajax({
			url: 'list.json',
			type: 'get',
			dataType: 'json',
			success: function(responseText){ 
				$('#staffList').empty();
				$.each(responseText.staff, function(i, staff){
					var tr = '<tr>';
					tr += '<td>' + staff.sno + '</td>';
					tr += '<td>' + staff.name + '</td>';
					tr += '<td>' + staff.dept + '</td>';
					tr += '<td>' + staff.salary + '</td></tr>';
					$('#staffList').append(tr);
				})
			}
		})
	}
	
	function fnAdd(){
		$('#btnAdd').on('click', function() {
			$.ajax({
				url: 'add.json',
				type: 'post',    
				data: $('#addStaff').serialize(),   
				dataType: 'json',  
				success: function(responseText){
					if(responseText.res == 1) {
						alert('사원 등록이 성공했습니다.');
						fnList();
						$('#sno').val('');
						$('#name').val('');
						$('#dept').val('');
					}
				}, 
				error: function(jqXHR) {
					alert(jqXHR.status)
					alert(jqXHR.responseText)
				}
			})
		})
	}
	
	function fnDetail(){
		$('#btnDetail').on('click', function() {
			$.ajax({
				url: 'query.json',
				data: 'query=' + $('#sno1').val(),
				type: 'get',     
				dataType: 'json',
				success: function(responseText) {
					$('#staffList').empty();
					$(function(){
						var tr = '<tr>';
						tr += '<td>' + responseText.sno + '</td>';
						tr += '<td>' + responseText.name + '</td>';
						tr += '<td>' + responseText.dept + '</td>';
						tr += '<td>' + responseText.salary + '</td>';
						$('#staffList').append(tr);
					})
				}
			}) 
		})
	}
	
	
	function fnAll(){
		$('#btnList').on('click', function() {
			$.ajax({
				url: 'list.json',
				type: 'get',
				dataType: 'json',
				success: function(responseText){ 
					$('#staffList').empty();
					$.each(responseText.staff, function(i, staff){
						var tr = '<tr>';
						tr += '<td>' + staff.sno + '</td>';
						tr += '<td>' + staff.name + '</td>';
						tr += '<td>' + staff.dept + '</td>';
						tr += '<td>' + staff.salary + '</td></tr>';
						$('#staffList').append(tr);
					})
				}
			})
		})
	}
	
		
</script>
</head>
<body>
	<h1>사원등록</h1>
	<div>
		<form id="addStaff">
			<label for="sno">
				<input type="text" name="sno" id="sno" placeholder="사원번호">
				<span id="sno_check_result"></span>
			</label>
			<label for="name">
				<input type="text" name="name" id="name" placeholder="사원명">
			</label>
			<label for="dept">
				<input type="text" name="dept" id="dept" placeholder="부서명">
			</label>
			<input type="button" value="등록" id="btnAdd">
		</form>
	</div>
	<hr>
	<h1>사원조회</h1>
	<div>
		<form id="detailStaff">
			<label for="sno1">
				<input type="text" name="sno1" id="sno1" placeholder="사원번호입력">
			</label>
				<input type="button" value="조회" id="btnDetail">
				<input type="button" value="전체" id="btnList">
		</form>
	</div>
	<hr>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>사원번호</td>
					<td>사원명</td>
					<td>부서명</td>
					<td>연봉</td>
				</tr>
			</thead>
			<tbody id="staffList">
			</tbody>
		</table>
	</div>
</body>
</html>