<%@ page import="kh.test.jdbckh.board.model.dto.BoardDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style>
.wrap-flex {
	display: flex;
}

.wrap-grid {
	display: grid;
	grid-template-columns: auto auto auto auto auto;
}
</style>
</head>
<body>


	<div>
		[<%=request.getSession().getAttribute("SsLoginId")%>]
		<hr>
		[<%=request.getAttribute("SsLoginId")%>]
		<hr>
		[${SsLoginId }]
		<hr>
		[${session.SsLoginId }]
		<hr>
		[${successMsg }]
		<hr>
		[${successFailMsg }]
		<hr>
		<script>
			var msg = '${successFailMsg}';
			if (msg) {
				alert(msg);
			}
		</script>
	</div>

	<div>
		<c:choose>
			<c:when test="${not empty SsLoginId }">
				<a href="${pageContext.request.contextPath }/logout">로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath }/login">로그인</a>
			</c:otherwise>
		</c:choose>
	</div>

	[[초기 확인용
	<%=request.getAttribute("boardList")%>]]
	<h2>게시글</h2>
	<div>
		<a href="<%=request.getContextPath()%>/board/insert">새 글 등록</a>
	</div>
	<div class="wrap-grid">
		<div>번호</div>
		<div>제목</div>
		<div>작성자</div>
		<div>작성일</div>
		<div>옵션</div>

		<c:if test="${not empty boardList }">

		</c:if>

		<%
		List<BoardDto> boardList = (List<BoardDto>) request.getAttribute("boardList");
		if (boardList != null && boardList.size() > 0) {
			for (int i = 0; i < boardList.size(); i++) {
				BoardDto vo = boardList.get(i);
		%>
		<div><%=vo.getBno()%></div>
		<div><%=vo.getBtitle()%></div>
		<div><%=vo.getMid()%></div>
		<div><%=vo.getBwriteDate()%></div>
		<div>
			<span><a href="<%=request.getContextPath()%>/board/insert">답글</a></span>
		</div>
		<%
		}
		}
		%>
	</div>
</body>
</html>