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
	Here are your favorites!
	<br>
	<c:forEach items="${faves}" var="f">
	<img src="${f.imgloc }" style ='height:400px'> <br>
	</c:forEach>
	
	<a href="userpage"> Return to userpage </a>
	
</body>
</html>