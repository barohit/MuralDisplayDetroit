<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
</head>
</head>
<body>
	<form action="loggingin" method="post">
		Username: <input type="text" name="username"> <br />
		Password: <input type="password" name="password"> <br /> <input
			type="submit" value="log in">
	</form>
	<br /> Don't have an account?
	<a href="create"> Create One </a>
</body>
</html>