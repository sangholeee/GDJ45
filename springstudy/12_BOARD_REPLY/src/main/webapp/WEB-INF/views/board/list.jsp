<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">

</script>
<style>

	.link, .unlink {
	position: relative;
	margin: 5px;
	padding: 3px;
	border: 1px solid #fff;
	font-family: tahoma, helvetica, sans-serif;
	color: #999;
	text-align: center;
	text-decoration: none;
	}
	.link:hover {
	border: 1px solid orange;
	color: #00c73c;
	}
	td {
	padding: 5px;
	border-top: 1px solid silver;
	border-bottom: 1px solid silver;
	text-align: center;
	}

</style>
</head>
<body>

	<!-- 
	Session에 올라가있는 정보는 언제든 el 처리해서 호출 가능하다. 
	${user.id} / ${user.pw} / ${user.name}
	-->
	
	<h3>게시판</h3>
	
	<c:if test="${user != null}">  <!-- user가 null이 아니다. = Session에 정보가 들어있다. = 로그인이 성공했다. -->
		<a href="${contextPath}/board/savePage">새글작성</a>
		<a href="${contextPath}/board/index">로그아웃</a>
	</c:if>
	<c:if test="${user == null}">
		<a href="${contextPath}/board/index">로그인페이지로 이동</a>
	</c:if>
	
	<hr>
	
	<table>
		<thead>
			<tr>
				<td>순번</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일자</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards}" var="board">
				<tr>
					<td>${totalRecord + 1 - board.rn}</td>
					<td><a href="${contextPath}/board/detail?boardNo=${board.boardNo}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.hit}</td>
					<td>${board.created}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>