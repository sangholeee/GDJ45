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

</style>
</head>
<body>
	
	<a href="${contextPath}/gallery/savePage">새글 작성</a>
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>대표이미지</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${galleries}" var="fa" varStatus="vs">
				<tr>
					<td>${beginNo - vs.index}</td>
					<td><img alt="${fa.origin}" src="${contextPath}/gallery/display?fileAttachNo=${fa.fileAttachNo}&type=thumb"></td>   <!-- 실무에서는 realPath X -->
					<td>${fa.gallery.title}</td>
					<td>${fa.gallery.writer}</td>
					<td>${fa.gallery.hit}</td>
					<td>${fa.gallery.created}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>