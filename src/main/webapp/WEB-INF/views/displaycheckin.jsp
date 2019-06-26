<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Murals you've checked in at</title>

<!-- add requestmapping displaycheckins in controller -->
</head>
<body>
	<h3>Here are the murals you've checked into!</h3>
	<br>
	<c:forEach items="${check}" var="c">
	<img src="${c.imgloc }" style ='height:400px'> <br>
	
	${c.name }<br>
	Artist: ${c.artistname }<br>
	<a href = "https://www.google.com/maps/dir//${c.address }" target="_blank">${c.address }</a><br>
	<br>
	
	</c:forEach>
	
	<a href="userpage"> Return to userpage </a>
	
</body>
</html>