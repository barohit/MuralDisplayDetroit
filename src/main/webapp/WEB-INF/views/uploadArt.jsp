<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css" />
<title>Upload Art</title>
<script type="text/javascript" src="validate.js"></script>
</head>
<body>
<font size="5">Choose upload picture of mural and fill in details: <br /> <br /></font>
<form action="upload" method="POST" enctype="multipart/form-data" modelAttribute="fileUpload"> 
	Upload file: <input type="file" name="picture"><br><tab1> <br /> 
	<tab1> Name: <input type="text" name="name"> <br /> <br /></tab1>
	<tab1>Artist: <input type="text" name="artist"> <br /> <br /></tab1>
	<tab1>Address: <input type="text" name="address"> <br /> <br /></tab1>
	<tab1>Neighborhood: <input type="text" name="neighborhood"> <br /> <br /></tab1>
	<tab1><input type="submit" onclick="singleInputTypeCheck()"></tab1>
</form>
</body>
</html>
