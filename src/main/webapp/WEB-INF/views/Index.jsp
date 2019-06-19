<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
</head>

<p><a href="/login">Login</a></p>
<p><a href="/art_near_me">Art Near Me</a></p>
<p><a href="/display_all_art">Display All Art</a> </p>
<p><a href="/upload_art">Upload Art</a>	</p>
<br>
<img src="${pic }" style ='height:600px'> 

</body>
</html>