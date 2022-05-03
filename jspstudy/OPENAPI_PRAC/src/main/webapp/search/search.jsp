<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>  
		<form id="formSearch">
			<label for="query">
				검색
				<input type="text" name="query" id="query">
			</label>
			<label for="display">
				검색결과건수
				<select name="display" id="display">
					<option value="10" selected>10</option>
					<option value="20">20</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="80">80</option>
					<option value="100">100</option>
				</select>
			</label>
			<label for="sim">유사도순<input type="radio" name="sort" id="sim" value="sim" checked></label>
			<label for="date">날짜순<input type="radio" name="sort" id="date" value="date"></label>
			<label for="asc">낮은가격순<input type="radio" name="sort" id="asc" value="asc"></label>
			<label for="dsc">높은가격순<input type="radio" name="sort" id="dsc" value="dsc"></label>
			<input type="button" value="검색" id="btnSearch">
		</form>    
	</div>       

</body>
</html>