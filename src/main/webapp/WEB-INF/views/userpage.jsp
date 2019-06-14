<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello ${user.username}, you have logged in! <br />
	
	<a href="favorites?user=${user}"> Favorites </a>
	
</body>
</html>