<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 err</title>
</head>
<body>


	<%=exception.getClass().getName()%>
	<hr>
	this is 404 err page Info :
	<a href="${pageContext.request.contextPath }/main">메인으로 이동</a>


</body>
</html>