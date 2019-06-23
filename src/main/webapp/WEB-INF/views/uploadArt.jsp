<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Art</title>
</head>
<body>
Choose upload picture of mural and fill in details: <br /> <br />
<form action="upload" method="POST" enctype="multipart/form-data" modelAttribute="fileUpload"> 
	Upload file: <input type="file" name="picture"> OR: <br /> 
	Copy and Paste URL <input type="url" name="url"> <br /> <br /> 
	Name: <input type="text" name="name"> <br /> <br />
	Artist: <input type="text" name="artist"> <br /> <br />
	Address: <input type="text" name="address"> <br /> <br />
	Neighborhood: <input type="text" name="neighborhood"> <br /> <br />
	<input type="submit" onclick="singleInputTypeCheck()">
</form>
</body>
</html>