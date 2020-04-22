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

	<h1>게시글 보기</h1>
	
	작성자 : ${contentDataVo.memberVo.m_nick }<br>	<%-- 4가지 객체 순서대로 찾고 뽑아서 출력! --%>
	제목 : ${contentDataVo.boardVo.b_title }<br>
	내용 <br>
	${contentDataVo.boardVo.b_content }<br>
	<br><br>
	
	<a href="./main_page.do">목록</a>
	<%-- 수정, 삭제 본인확인(로그인을 했는지, 글쓴이와 같은지 확인) --%>
	<c:if test="${!empty sessionUserInfo && sessionUserInfo.m_no == contentDataVo.memberVo.m_no }">
		<a href="./delete_content_process.do?b_no=${contentDataVo.boardVo.b_no }">삭제</a>
		<a href="./update_content_page.do?b_no=${contentDataVo.boardVo.b_no }">수정</a>		<%-- 수정은 페이지 나와야한다. --%> 
	</c:if>

</body>
</html>