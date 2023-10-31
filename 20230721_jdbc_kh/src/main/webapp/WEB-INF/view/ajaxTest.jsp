<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax test</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
</head>
<body>
	<h2>ajaxtest</h2>
	<button id="btnajax1">ajax1</button>
	<script>
	$("#btnajax1").click();
	function ajax1ClickHandler(){
		console.log("btnajax1 click");
		// $.ajax("object형태로 매개인자 전달해야 함"); 
		// var obj={k1:12,k2:'dskjsdf',ks:function(){}};
		$.ajax({url:"${pageContext.request.contextPath}/ajax1"});
	}
	</script>
</body>
</html>