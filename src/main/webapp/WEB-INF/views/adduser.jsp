<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="validate.js"></script>
</head>
<body>
	Please fill in all of the fields below: 
	<form action="confirmation">
		Username: <input type="text" name="username">
		Password: <input type="text" name="password">
		Confirm Password: <input type="text" name="confirmpassword">
		<input type="submit" onclick="return validate()">
	</form>

</body>
</html>