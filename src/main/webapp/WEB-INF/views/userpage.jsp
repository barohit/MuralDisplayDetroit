<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
input.submit_hyperlink {
    background:none!important;
     border:none; 
     padding:0!important;
     font-size: 19px;
     text-decoration:underline;
}

input.submit_hyperlink:hover{
         text-decoration:underline;
}
</style>
<meta charset="UTF-8">
<title>User Page</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
<script type="text/javascript" src="validate.js"></script>
</head>
<body>
	<p style="font-size:19px">Hello ${user.username}, you have logged in! </p><br />
	
	<form action="faves">
		<input type="hidden" value="${user.userid}" name="user">
		<input type="submit" value="favorites" class="submit_hyperlink" value="submit this form"/>
		
	</form><br>
	<p style="font-size:18px"><a href="/art_near_me">Art Near Me</a></p>
	<p style="font-size:18px"><a href="display_all_art"> Add favorites: </a></p>
	<p style="font-size:18px">	<a href="recommendations"> Recommendations </a></p>
	
	<p style="font-size:18px"><a href="logout"> Log Out </a></p>

</body>
</html>