<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checked in Mural</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
</head>
</head>
<body>
	<img src="${mural.imgloc }" style='height: 400px'>
	<p style="font-size: 18px">Congratulations, you have checked in at
		${mural.name }!</p>
	<p style="font-size: 18px">
		<a href="/">Back to home page</a>
	</p>

</body>
</html>