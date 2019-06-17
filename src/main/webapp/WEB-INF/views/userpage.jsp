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
	<form action="faves">
		<input type="hidden" value="${user.userid}" name="user">
		<input type="submit" value="favorites">
	</form>

	<a href="display_all_art?user=${user}"> Add favorites: </a>
	
	

</body>
</html>