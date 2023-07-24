<%@page import="kh.test.jdbckh.department.model.vo.DepartmentVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학과 상세</title>
</head>
<body>
	<h2>학과 상세</h2>
	
	<% DepartmentVo vo = (DepartmentVo)request.getAttribute("svo"); %>

	<table border ="1">
		<tr>
			<th>학과 번호</th>
			<th>학과 이름</th>
			<th>계열</th>
			<th>개설 여부</th>
			<th>정원</th>
		</tr>
		<tr>
			<td><%=vo.getDepartmentNo() %></td>
			<td><%=vo.getDepartmentName() %></td>
			<td><%=vo.getCategory() %></td>
			<td><%=vo.getOpenYn() %></td>
			<td><%=vo.getCapacity() %></td>
		</tr>
	</table>
</body>
</html>