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
		String tel = request.getParameter("tel");
	%>
	
	<form action="/JSP/prac/03_prac3.jsp">
		<input type="text" name="address" placeholder="주소">
		<input type="hidden" name="tel" value="<%=tel%>">
		<button>전송</button>
	</form>

</body>
</html>