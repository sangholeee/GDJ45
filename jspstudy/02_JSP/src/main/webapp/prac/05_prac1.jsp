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
		// request에 속성으로 저장
		request.setAttribute("name", "민경태");
		request.setAttribute("age", 45);
		
		// forward(전달, request와 response를 전달한다.)
		request.getRequestDispatcher("05_prac2.jsp").forward(request, response);
		
		
	%>

</body>
</html>