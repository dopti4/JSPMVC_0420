<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:choose>		<%-- else if 느낌 --%>
		<c:when test="${!empty sessionUserInfo }">						<!-- null이 아니면~ -->
			${sessionUserInfo.m_nick }님 환영합니다!<br>				<!-- getM_nick 호출 -->
			<a href="./logout_process.do">로그아웃</a>
		</c:when>
		<c:otherwise>
			비회원으로 접근하였습니다.	<a href="./login_page.do">로그인</a><br>
		</c:otherwise>
	</c:choose>
	<br>
	
	<h1>게시판 제목 리스트</h1>
	
	
	<%-- 담았던 거 꺼내준다~ --%>
	<table border="1">
		<tr><td>글 번호</td><td>제목</td><td>작성자</td><td>작성일</td></tr>
		<c:forEach items="${contentList }" var="data">		<%-- items : 배열, var : 변수명 --%>
			<tr>
				<td>${data.boardVo.b_no }</td>
				<td><a href="./read_content_page.do?b_no=${data.boardVo.b_no }">${data.boardVo.b_title }</a></td>
				<td>${data.memberVo.m_nick }</td>
				<td>${data.boardVo.b_writedate }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<c:if test="${!empty sessionUserInfo }">
		<a href="./write_content_page.do">글쓰기</a>
	</c:if>

</body>
</html>