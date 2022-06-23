<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<c:if test="${loginMember ne null}">			
		<h2> 네이버 아이디 로그인 성공하셨습니다!! </h2>			
		<h3>'${loginMember.name}' 님 환영합니다! </h3>            	
		<h3>'${loginMember.id}'  </h3>            	
		<h3>'${loginMember.email}'  </h3>            	
		<h3>'${loginMember.gender}'  </h3>            	
		<h3>'${loginMember.birth}'  </h3>            	
		<h3>'${loginMember.mobile}'  </h3>            	
		<h3><a href="logout">로그아웃</a></h3> 	
	</c:if>	
			
	<c:if test="${loginMember eq null}">	
		<form action="login.userdo" method="post" name="frm"  style="width:470px;">				
		<h2>로그인</h2>				  
		<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="아이디" value="${id}"> <br>				  
		<input type="password" id="pwd" name="pwd" class="w3-input w3-border" placeholder="비밀번호" >	<br>				
		<input type="submit" value="로그인" onclick="#"> <br>			</form>			<br>						
		
		<!-- 네이버 로그인 창으로 이동 -->			
		<div id="naver_id_login" style="text-align:center">
		<a href="${login}">			
		<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
		</a>
		</div>			
		<br>
	</c:if>				
</body>
</html>