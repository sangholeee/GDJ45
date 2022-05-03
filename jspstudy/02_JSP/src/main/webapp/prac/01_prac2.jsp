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
	
		// 자바 변수는 "표현식"으로 읽는다.
		String name = request.getParameter("name");
		
		// 파라미터 name은 request에 저장되어 있는데,
		// EL로 표현할 수 없는가?
		// ${속성}은 표현할 수 있으나 파라미터는 방법이 다르다.
		// ->${param.name}
	%>
	
	<h3>이름 : <%=name%></h3>
	<h3>이름 : ${param.name}</h3>

</body>
</html>