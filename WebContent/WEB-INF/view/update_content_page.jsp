<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시글 수정</h1>
	<form action="./update_content_process.do" method="post">
		작성자 : ${contentDataVo.memberVo.m_nick }<br>
		제목 : <input type="text" value="${contentDataVo.boardVo.b_title }" name="b_title"><br>
		내용 <br>
		<%-- textarea는 value 없으니 사이에 써야함 --%>
		<textarea rows="10" cols="40" name="b_content">${contentDataVo.boardVo.b_content }</textarea><br>
		<input type="hidden" value="${contentDataVo.boardVo.b_no }" name="b_no">	
		<br><br>
		<input type="submit" value="수정">
		
		
	</form>

</body>
</html>