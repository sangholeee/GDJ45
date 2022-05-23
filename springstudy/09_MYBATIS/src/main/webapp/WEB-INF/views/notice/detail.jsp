<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="" method="">
		
		공지번호 ${notice.noticeNo}<br>
		제목 ${notice.title}<br>
		내용<br>
		<pre>${notice.content}</pre>
		조회수 ${notice.hit}<br>
		작성일 ${notice.created}<br>
		수정일 ${notice.lastModified}<br>
		
		
	</form>

</body>
</html>