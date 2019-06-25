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
	<img src="${r.imgloc}" style ='height:400px'> <br>
	<a href = "https://www.google.com/maps/dir//${r.address }" target="_blank">${r.address }</a><br>
	Artist: ${r.artistname }<br>
	<form action="addrecs">
		<input type="hidden" name="muralid[]" value="${r.muralid}">
		<input type="hidden" name="user" value="${user.userid}">
		<input type="submit" value="Add this to Favorites">
	</form>
	<br>
	</c:forEach>
	<br>
	<a href="userpage"> Back to user page </a>
</body>
</html>