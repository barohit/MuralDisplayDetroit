<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
<script type="text/javascript" src="validate.js"></script>
</head>
<body>
	Please fill in all of the fields below: 
	<form action="confirmation" method = "post">
		Username: <input type="text" name="username">
		Password: <input type="password" name="password">
		Confirm Password: <input type="password" name="confirmpassword">
		<input type="submit" onclick="return validate()">
	</form>
<p id="passvord"></p>
</body>
</html>