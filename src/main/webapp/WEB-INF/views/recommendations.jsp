<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recommendations </title>
</head>
<body>
	<c:forEach items="${recommendations}" var="r">
	<img src="${r.imgloc}">
	<form action="addrecs">
		<input type="hidden" name="muralid[]" value="${r.muralid}">
		<input type="hidden" name="user" value="${user.userid}">
		<input type="submit" value="Add this to Favorites">
	</form>
	</c:forEach>
	<br>
	<a href="userpage"> Back to user page </a>
</body>
</html>