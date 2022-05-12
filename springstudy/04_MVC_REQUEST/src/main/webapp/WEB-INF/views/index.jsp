<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	<!-- Controller1, 4번 공부해야함 -->

	<!-- Controller1에 요청하기 -->
	<a href="${contextPath}/detail1?name=민경태&age=45">정보전달1</a><br>
	<a href="${contextPath}/detail2?name=민경태&age=45">정보전달2</a><br>
	<a href="${contextPath}/detail3?name=민경태&age=45">정보전달3</a><br>
	<a href="${contextPath}/detail4?name=민경태&age=45">정보전달4</a><br>
	<a href="${contextPath}/detail5?name=민경태&age=45">정보전달5</a><br>
	

	<hr>

	<!-- Controller2에 요청하기, Board 도메인(Builder 패턴) 사용-->
	<a href="${contextPath}/add1?title=공지사항&hit=10">정보전달1</a><br>
	<a href="${contextPath}/add2?title=공지사항&hit=10">정보전달2</a><br>
	<a href="${contextPath}/add3?title=공지사항&hit=10">정보전달3</a><br>
	
	<hr>
	
	<!-- Controller3에 요청하기, Product 도메인 사용 -->
	<form method="post">
		<input type="text" name="model" placeholder="모델명"><br>
		<input type="text" name="price" placeholder="가격"><br>
		<input type="button" value="정보전달1" onclick="fn1(this.form)">   <!-- HttpServletRequest + Model -->
		<input type="button" value="정보전달2" onclick="fn2(this.form)">   <!-- @RequestParam + Model -->
		<input type="button" value="정보전달3" onclick="fn3(this.form)">   <!-- Product + Model -->
	</form>
	<script>
		function fn1(f) {    <!-- onclick=fn1(this.form)을 통해서 from 전체 내용이 매개변수 f에 전달된다. -->
			f.action = '${contextPath}/remove1';
			f.submit();
		}
		function fn2(f) {    <!-- onclick=fn2(this.form)을 통해서 from 전체 내용이 매개변수 f에 전달된다. -->
			f.action = '${contextPath}/remove2';
			f.submit();
		}
		function fn3(f) {    <!-- onclick=fn3(this.form)을 통해서 from 전체 내용이 매개변수 f에 전달된다. -->
			f.action = '${contextPath}/remove3';
			f.submit();
		}
	</script>
	
	<hr>
	
	<!-- Controller4에 요청하기, redirect 하는 방법 -->
	<a href="${contextPath}/list1?page=1">목록보기1</a><br>
	<a href="${contextPath}/list3?page=1">목록보기2</a><br>
	<a href="${contextPath}/list5?page=1">목록보기3</a><br>
	
</body>
</html>