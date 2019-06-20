<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Neighborhood</title>
</head>
<body>
 
<c:forEach items="${list}" var="l">
	<h2>Neighborhood: ${l.key }</h2>
	<c:forEach items="${l.value }" var="m">
	<img src="${m.imgloc }" style ='height:400px'> <br>
	${m.artistname }
	<br>
	</c:forEach>
</c:forEach>
	
	<a href="userpage"> Return to user page </a>

</body>
</html>