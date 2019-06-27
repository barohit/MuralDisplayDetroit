<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Art</title>
<script type="text/javascript" src="validate.js"></script>
</head>
<body>
Choose upload picture of mural and fill in details: <br /> <br />
<form action="upload" method="POST" enctype="multipart/form-data"> 
	Upload file: <input type="file" name="picture">  
	Name: <input type="text" name="name"> <br /> <br />
	Artist: <input type="text" name="artist"> <br /> <br />
	Address: <input type="text" name="address"> <br /> <br />
	Neighborhood: <input type="text" name="neighborhood"> <br /> <br />
	<input type="submit" onclick="return InputTypeCheck()">
</form>
</body>
</html>