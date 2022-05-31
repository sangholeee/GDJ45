<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	/*
	$(function(){
		$('.reply_link').on('click', function(){
			$(this).parent().parent().next().toggleClass('reply_form');
		})
	})
	*/
	
	$(function(){
		
		$('.reply_link').on('click', function(){
			$('.reply_form').addClass('blind');
	    	$(this).parent().parent().next().removeClass('blind');
		})
	
	})

</script>
<style>
	.blind {
		display: none;
	}
	.reply_link{
		cursor: pointer
	}
	.link, .unlink {
	position: relative;
	margin: 5px;
	padding: 3px;
	border: 1px solid #fff;
	font-family: tahoma, helvetica, sans-serif;
	color: #999;
	text-align: center;
	text-decoration: none;
	}
	.link:hover {
	border: 1px solid orange;
	color: #00c73c;
	}
	td {
	padding: 5px;
	border-top: 1px solid silver;
	border-bottom: 1px solid silver;
	text-align: center;
	}
</style>
</head>
<body>

	<h3>게시글 작성 화면</h3>
	<c:if test="${member.id ne null }">
		<form action="${contextPath}/freeBoard/saveFreeBoard" method="post">
			<input type="text" name="writer" value="${member.id}" readonly>
			<input type="text" name="content" placeholder="내용">
			<button>작성완료</button>
		</form>
		<a href="${contextPath}/index">로그아웃</a>
	</c:if>
	<c:if test="${member.id eq null}">
		<a href="${contextPath}/index">로그인 화면으로 가기</a>
	</c:if>
	
	<hr>
	
	<table>
		<caption>${totalRecord}개 게시글</caption>
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty freeBoards}">
				<tr>
					<td colspan="5"> 첫 게시글을 작성해주세요.</td>
				</tr>
			</c:if>
			<c:if test="${not empty freeBoards}">
				<c:forEach var="fb" items="${freeBoards}">
					<c:if test="${fb.state == -1}">
						<tr>
							<td>${totalRecord - fb.rowNum + 1}</td>
							<td colspan="4">삭제된 게시글입니다.</td>
						</tr>
					</c:if>
					<c:if test="${fb.state == 1}">
						<tr>
							<td>${totalRecord - fb.rowNum + 1}</td>
							<td>${fb.writer}</td>
							<td>
								<!-- Depth 만큼 들여쓰기(Depth 1 == Space 2), step="1" -> 1씩 증가, 생략 가능 -->
								<c:forEach begin="1" end="${fb.depth}" step="1">&nbsp;&nbsp;</c:forEach>
								<!-- 댓글은 re 표시, 크다: gt, 작거나 같다: le -->
								<c:if test="${fb.depth gt 0}"><i class="fa-brands fa-replyd"></i></c:if>
								<!-- 내용 -->
								<c:if test="${fb.content.length() gt 20}">
									${fb.content.subString(0, 20)}
								</c:if>
								<c:if test="${fb.content.length() le 20}">
									${fb.content}
								</c:if>
								<!-- 답글 달기 하나로 제한(if가 사라지면 다단댓글 허용) -->
								<!-- reply_link의 부모 <td>의 부모 <tr>의 다음 형제(next)의 <tr>에 있는 reply_form을 조작해보겠다. -->
								<%-- <c:if test="${fb.depth eq 0}"> 주석처리 -> Ctrl + Shift + / --%>
									<a class="reply_link">답글</a>
								<%-- </c:if> --%>
							</td>
							<td>${fb.created}</td>
							<td>
								<c:if test="${member.id eq fb.writer}">
									<input type="button" value="삭제" onclick="fnRemove(this)">
									<script>
										function fnRemove() {
											if(confirm('삭제할까요?')){
												location.href='${contextPath}/freeBoard/remove?freeBoardNo=${fb.freeBoardNo}';
											}
										}
											
									</script>
								</c:if>
							
							</td>
						</tr>
						<!-- class 속성값 reply_form을 가지고 있으면 안 보인다. -->
						<c:if test="${member.id != null}">
							<tr class="reply_form blind">
								<td colspan="5">
									<form action="${contextPath}/freeBoard/saveReply" method="post">
										<input type="text" name="writer" value="${member.id}" readonly size="4">
										<input type="text" name="content" placeholder="내용" size="40">
										
										<!-- 원글의 depth, groupNo, groupOrd -->
										<input type="hidden" name="depth" value="${fb.depth}">
										<input type="hidden" name="groupNo" value="${fb.groupNo}">
										<input type="hidden" name="groupOrd" value="${fb.groupOrd}">
										<button>답글달기</button>
									</form>
								</td>
							</tr>
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					${paging}
				</td>
			<tr>
		</tfoot>
		
	</table>
	
</body>
</html>