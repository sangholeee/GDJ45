<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
	%>
	
	<a href="/JSP/prac/02_prac3.jsp?name=<%=name%>">데이터 전송</a>
	<a href="/JSP/prac/02_prac3.jsp?name=${param.name}">데이터 전송</a>

</body>
</html>