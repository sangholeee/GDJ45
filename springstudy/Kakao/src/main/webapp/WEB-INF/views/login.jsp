<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="resources/js/jquery-3.6.0.js"></script>

<script>
	Kakao.init('87438f60ac5c9dfc1cee881a56b49625'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	
	$(function(){
		kakaoLogin();
		kakaoLogout();
	})
	
	//카카오로그인
	function kakaoLogin() {
		$('#kakaoLogin').on('click', function(){
		    Kakao.Auth.login({
		      success: function (response) {
		        Kakao.API.request({
		          url: '/v2/user/me',
		          success: function (response) {
		        	  sessionStorage.setItem("id", response.id);
		        	  sessionStorage.setItem("birthday", response.kakao_account.birthday);
		        	  sessionStorage.setItem("gender", response.kakao_account.gender);
		        	  sessionStorage.setItem("email", response.kakao_account.email);
		        	  sessionStorage.setItem("name", response.properties.nickname);
		        	  var id = sessionStorage.getItem("id")
		        	  var birthday = sessionStorage.getItem("birthday")
		        	  var gender = sessionStorage.getItem("gender")
		        	  var email = sessionStorage.getItem("email")
		        	  var name = sessionStorage.getItem("name")
		        	  
		        	  
		        	  
		          },
		          fail: function (error) {
		            console.log(error)
		          },
		        })
		      },
		      fail: function (error) {
		        console.log(error)
		      },
		    })
		})
	  }
	//카카오로그아웃  
	function kakaoLogout() {
		$('#kakaoLogout').on('click', function(){
		    if (Kakao.Auth.getAccessToken()) {
		      Kakao.API.request({
		        url: '/v1/user/unlink',
		        success: function (response) {
		        	console.log(response)
		        },
		        fail: function (error) {
		          console.log(error)
		        },
		      })
		      Kakao.Auth.setAccessToken(undefined)
		    }
		})
	  }  
</script>


</head>
<body>

		<ul>
			<li id="kakaoLogin">
		      <a href="javascript:void(0)">
		          <span>카카오 로그인</span>
		      </a>
			</li>
			<li id="kakaoLogout">
			      <a href="javascript:void(0)">
			          <span>카카오 로그아웃</span>
			      </a>
			</li>
		</ul>
		
		<c:if test="${not empty response}">
			<h2> 카카오 아이디 로그인 성공하셨습니다!! </h2>			
			<h3>'${loginMember.properties.nickname}'  님 환영합니다!  </h3>            	
			<h3>'${loginMember.id}'</h3>            	
			<h3>'${loginMember.kakao_account.birthday}'  </h3>            	
			<h3>'${loginMember.kakao_account.gender}'  </h3>            	
			<h3>'${loginMember.kakao_account.email}'  </h3>            	
		</c:if>

</body>
</html>