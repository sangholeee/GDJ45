<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.link, .unlink {
	position: relative;
	margin: 5px;
	padding: 5px;
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

	<table border=1>
		<thead>
			<tr>
				<td>순번</td>
				<td>사원번호</td>
				<td>사원명</td>
				<td>입사일자</td>
				<td>연봉</td>
				<td>부서번호</td>
				<td>부서이름</td>
			<tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="emp" varStatus="vs">
				<tr>
					<td>${beginNo - vs.index}</td>
					<td>${emp.employeeId}</td>
					<td>${emp.firstName}</td>
					<td>${emp.hireDate}</td>
					<td>${emp.salary}</td>
					<td>${emp.departmentId}</td>
					<td>${emp.departmentName}</td>
				<tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>