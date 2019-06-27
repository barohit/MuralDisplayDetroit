<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Here are the murals you checked in at!
	<c:forEach items="${murals }" var="m">
		<img src="${m.imgloc }">
		<p>Name: ${m.name }</p>
	</c:forEach>
</body>
</html>