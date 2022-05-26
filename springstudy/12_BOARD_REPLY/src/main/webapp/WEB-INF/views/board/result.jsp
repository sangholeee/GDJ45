<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script>
	
	if('${kind}' == 'insert') {
		if('${insRes}' > '0') {
			alert('등록 성공');
			location.href='${contextPath}/board/list'
		} else {
			alert('등록 실패');
			history.back();
		}
	}
	
	if('${kind}' == 'update') {
		if('${updRes}' > '0') {
			alert('수정 성공');
			location.href='${contextPath}/board/list'
		} else {
			alert('수정 실패');
			history.back();
		}
	}
	
	if('${kind}' == 'delete') {
		if('${delRes}' > '0') {
			alert('삭제 성공');
			location.href='${contextPath}/board/list'
		} else {
			alert('삭제 실패');
			history.back();
		}
	}
	
</script>