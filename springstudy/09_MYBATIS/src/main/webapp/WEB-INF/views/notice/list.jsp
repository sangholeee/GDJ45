<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script src="http://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script>

	// ${notice.created.substring(0,10)}  -> created 시간 원하는 자릿수로 조정하기

	$(document).ready(function(){
		
		swal("안녕하세요", "swal입니다.", "success");

		
		$('tbody tr').on('click', function(){
			// $(this)                          : 클릭한 행을 의미한다.
			//                                    <tr>...</tr>
			// $(this).find('.noticeNo')        : 클릭한 행 내부에 있는 class="noticeNo" 요소를 의미한다.
			//                                    <td class="noticeNo">1</td>
			// $(this).find('.noticeNo').text() : 클릭한 행 내부에 있는 class="noticeNo" 요소의 텍스트를 의미한다.
			//                                    1
			var noticeNo = $(this).find('.noticeNo').text();
			location.href='${contextPath}/notice/detail?noticeNo=' + noticeNo;
		})
		
		// 전체 선택 클릭하기
		// 전체 선택을 체크하면 개별 선택도 모두 체크
		// 천체 선택을 해제하면 개별 선택도 모두 해제
		var checkAll = $('#checkAll');
		var checkes = $('.checkes');
		checkAll.on('click', function(){
			for(let i = 0; i < checkes.length; i++) {
				// check[i].prop('checked', true);   -> 전체 선택이 체크된 경우
				// check[i].prop('checked', false);  -> 전체 선택이 해제된 경우
				$(checkes[i]).prop('checked', checkAll.prop('checked'));
			}
			/*  for 문이랑 같은 내용!
			$.each(checkes, function(i, check) {
				$(check).prop('checked', checkAll.prop('checked'));
			})
			*/
		})
		
		// 개별 선택 클릭하기
		// 개별 선택을 클릭하면
		// 모든 개별 선택을 확인해서
		// 모두 체크되어 있으면 전체 선택 체크하고,
		// 하나라도 체크 해제되어 있으면 전체 선택 해제한다.
		for(let i = 0; i < checkes.length; i++){
			$(checkes[i]).on('click', function(){
				for(let j = 0; j < checkes.length; j++){
					if($(checkes[j]).prop('checked') == false){  // 하나라도 체크 해제되었다면,
						checkAll.prop('checked', false);         // 전체 선택 해제하고,
						return;                                  // 함수 종료(클릭 이벤트 리스너)
					}
				}
				checkAll.prop('checked', true);             // 체크 해제된 것이 하나도 발견되지 않은 경우 전체 선택 체크
			})
		}
		
	})

</script>
</head>
<body>


	<a href="${contextPath}/notice/savePage">새 공지 작성</a>

	<hr>
	
	<form action="${contextPath}/notice/removeList">
	
		<button>선택삭제</button>
	
		<table border="1">
			<thead>
				<tr>
					<td><input type="checkbox" id="checkAll"></td>
					<td>번호</td>
					<td>제목</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${notices}" var="notice">
					<tr>
						<td><input type="checkbox" name="noticeNoList" class="checkes" value="${notice.noticeNo}"></td>
						<td class="noticeNo">${notice.noticeNo}</td>
						<td>${notice.title}</td>
						<td>${notice.created}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>