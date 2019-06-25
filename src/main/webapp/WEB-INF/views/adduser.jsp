<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
<script type="text/javascript" src="validate.js"></script>
</head>
<body>
	Please fill in all of the fields below: <br>
	<br>
	<form action="confirmation" method = "post">
		Username: <input type="text" name="username"><br>
		Password: <input type="password" name="password"><br>
		Confirm Password: <input type="password" name="confirmpassword"><br>
		<input type="submit" onclick="return validate()">
	</form>
<p id="passvord"></p>
</body>
</html>