<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Art</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/lux/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="design2.css" />
</head>

<body>

<p style="font-size:35px">Gallery</p>

 
<div class="dropdown">
  <button class="dropbtn"> Sort By</button>
  <div class="dropdown-content">

	<a href="neighborhood">Neighborhood</a>
	
  <a href="artist">Artist</a>
  </div>
</div>


<form action="addtofavorites">
	
<c:forEach items="${list}" var="l">
	<img src="${l.imgloc}" style ='height:400px'> <br>
	<a href = "https://www.google.com/maps/dir//${l.address }" target="_blank">${l.address }</a><br>
	Artist: ${l.artistname }<br>
	
	Add to favorites? <input type="checkbox" class="favorites" name="favorites[]" value="${l.muralid}">
	<br>
<br>
</c:forEach>
	<input type="hidden" name="favoritez" value="${userid}" >
	<input type="submit" value="submitfavorites">
 </form>
 



</body>
</html>