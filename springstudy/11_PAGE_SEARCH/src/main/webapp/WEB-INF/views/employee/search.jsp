<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	// 페이지 로드 이벤트
	$(function(){
		fnAreaChoice();
	})
	
	
	// 함수
	function fnAreaChoice(){
		
		// 초기 : 모두 숨김
		$('#equalArea, #rangeArea').css('display', 'none');
	
		// 선택
		$('#column').on('change', function(){
			if( $(this).val() == '' ) {
				$('#equalArea, #rangeArea').css('display', 'none');
			} else if( $(this).val() == 'EMPLOYEE_ID' || $(this).val() == 'FIRST_NAME' ) {
				$('#equalArea').css('display', 'inline');          // block(줄바꿈), inline(같은 줄에)
				$('#rangeArea').css('display', 'none');
			} else {
				$('#equalArea').css('display', 'none');         
				$('#rangeArea').css('display', 'inline');
			}
		})
		
		
		
	}
	
	
</script>
</head>
<body>

	<h1>사원검색</h1>
	
	<form id="f" method="get">
		<select name="column" id="column">
			<option value="">:::선택:::</option>
			<option value="EMPLOYEE_ID">사원번호</option>
			<option value="FIRST_NAME">이름</option>
			<option value="HIRE_DATE">고용일</option>
			<option value="SALARY">연봉</option>
		</select>
		<!-- <div>는 줄이 바뀌고, <span>은 줄이 바뀌지 않는다. -->
		<span id="equalArea">
			<input type="text" name="query" id="query" list="autoComplete">
			<datalist id="autoComplete"></datalist>
		</span>
		<!-- name은 백으로 보낼때 사용, id는 프론트에서 사용 -->
		<span id="rangeArea">
			<input type="text" name="begin" id="begin">
			~
			<input type="text" name="end" id="end">
		</span>
		<input type="button" value="검색" id="btnSearch">
		<input type="button" value="전체사원조회" id="btnSearchAll">
	</form>
	
	<br><hr><br>
	
	<!-- include 지시어 : 다른 jsp를 가져올 때 -->
	<%@ include file="list.jsp" %>

</body>
</html>