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

	// ${notice.created.substring(0,10)}  -> created 시간 원하는 자릿수로 조정하기

	$(document).ready(function(){
		
		$('tbody tr').on('click', function(){
			// $(this)                          : 클릭한 행을 의미한다.
			//                                    <tr>...</tr>
			// $(this).find('.noticeNo')        : 클릭한 행 내부에 있는 class="noticeNo" 요소를 의미한다.
			//                                    <td class="noticeNo">1</td>
			// $(this).find('.noticeNo').text() : 클릭한 행 내부에 있는 class="noticeNo" 요소의 텍스트를 의미한다.
			//                                    1
			var noticeNo = $(this).find('.noticeNo').text();
			location.href='${contextPath}/notice/detail?noticeNo=' + noticeNo;
		})
		
	})

</script>
</head>
<body>

	<a href="${contextPath}/notice/savePage">새 공지 작성</a>

	<hr>

	<table border="1">
		<thead>
			<tr>
				<td></td>
				<td>번호</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${notices}" var="notice">
				<tr>
					<td><input type="checkbox" name="noticeNoList" value="${notice.noticeNo}"></td>
					<td class="noticeNo">${notice.noticeNo}</td>
					<td>${notice.title}</td>
					<td>${notice.created}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>