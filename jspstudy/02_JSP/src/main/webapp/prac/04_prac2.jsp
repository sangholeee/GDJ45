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
		session.setAttribute("agree", request.getParameter("agree"));
	%>
	
	<form action="/JSP/prac/04_prac3.jsp">
		<input type="text" name="email" placeholder="이메일">
		<button>전송</button>		
	</form>

</body>
</html>