<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Favorites</title>
</head>
<body>
	<h3>Here are your favorite murals!</h3>
	<br>
	<c:forEach items="${faves}" var="f">
	<img src="${f.imgloc }" style ='height:400px'> <br>
	
	${f.name }<br>
	Artist: ${f.artistname }<br>
	<a href = "https://www.google.com/maps/dir//${f.address }" target="_blank">${f.address }</a><br>
	<br>
	
	</c:forEach>
	
	<a href="userpage"> Return to userpage </a>
	
</body>
</html>