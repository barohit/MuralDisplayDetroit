<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check In</title>

</head>
<body>
	<%--  <c:if test="${mural.length == 1 }">
		<img src="${mural[0].imgloc }">
		<p id="error"> Congratulations, you have checked in at ${mural[0].name }! </p>
	</c:if> --%>
	<form action="selectionCheckIn">
		<p style="font-size:18px"> Several murals are nearby. Select which one is yours </p>
		<c:forEach items="${mural}" var = "m">
			<img src="${m.imgloc }" style ='height:400px'> <br />
			<input type="radio" name="selection" value="${m.muralid }"> <br />
		</c:forEach>
		<input type="submit" value="check in">
		
	</form>
</body>
</html>