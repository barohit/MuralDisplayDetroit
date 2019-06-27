<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recommendations</title>
</head>
<body>
	<form action="addrecs">
		<c:forEach items="${recommendations}" var="r">
			<div>
				<img src="${r.imgloc}" style='height: 400px'> <br> <a
					href="https://www.google.com/maps/dir//${r.address }"
					target="_blank">${r.address }</a><br> Artist: ${r.artistname }
				<br /> Add to favorites? <input type="checkbox" name="muralid[]"
					value="${r.muralid}"> <br />
			</div>
		</c:forEach>
		<input type="hidden" name="userid" value="${user.userid}"> 
		<input type="submit" value="Add Recommendations">
	</form>

	<br>

	<br>
	<a href="userpage"> Back to user page </a>
</body>
</html>