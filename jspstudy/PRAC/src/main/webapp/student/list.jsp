<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="wrap">
		<h1 class="title">학생전체목록</h1>
		<div class="btn_area">
			<input type="button" value="학생등록하기" class="btn_insert" onclick="location.href='/PRAC/insertPage.do'">
		</div>
		<div>
			<input type="text" name="begin" id="begin" size="4" placeholder="최저점수">
			~
			<input type="text" name="end" id="end" size="4" placeholder="최고점수">
			<input type="button" value="조회" class="btn_search">
		</div>
		<table>
			<caption>전체 학생 수 : ${totalCount}명</caption>
			<thead>
				<tr>
					<td>학번</td>
					<td>성명</td>
					<td>국어</td>
					<td>영어</td>
					<td>수학</td>
					<td>평균</td>
					<td>학점</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${totalCount eq 0}">
					<tr>
						<td colspan="8">등록된 학생이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	

</body>
</html>