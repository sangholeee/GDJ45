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
		String email = request.getParameter("email");
		String agree = (String)session.getAttribute("agree");
	%>
	
	<h3>동의 : <%=agree%></h3>
	<h3>이메일 : <%=email%></h3>

</body>
</html>