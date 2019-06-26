<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>Home</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata">
<style>
body, html {
  height: 100%;
  font-family: "Inconsolata", sans-serif;
}

.bgimg {
  background-position: center;
  background-size: cover;
  background-image: url("${mural.imgloc }");
  min-height: 75%;
}

.menu {
  display: none;
}
</style>
<body onload="getLocation()">


<!-- Links (sit on top) -->
<div class="w3-top">
  <div class="w3-row w3-padding w3-black">
   <div class="w3-col s2">
      <a href="/login" class="w3-button w3-block w3-black">LOGIN</a>
    </div>
    <div class="w3-col s2">
      <a href="/art_near_me" class="w3-button w3-block w3-black">MURAL MAP</a>
    </div>
    <div class="w3-col s3">
      <a href="/display_all_art" class="w3-button w3-block w3-black"> DISPLAY ALL ART</a>
    </div>
    <div class="w3-col s2">
      <a href="/upload_art" class="w3-button w3-block w3-black">UPLOAD ART</a>
    </div>
   
    <div class="w3-col s3">
    	<form id="checkin" action="/checkin">
    		<input type="hidden" value="" name="lattitude">
    		<input type="hidden" value="" name="longitude">
    		<input type="button" onclick="submitCheckInForm()" value="CHECK IN" class="w3-button w3-block w3-black">
    	</form>
     </div>
    
  </div>
</div>

<!-- Header with image -->
<header class="bgimg w3-display-container w3-grayscale-min" id="home">
  <div class="w3-display-bottomleft w3-center w3-padding-large w3-hide-small">
    <span class="w3-tag">Artist: ${mural.artistname }</span>
  </div>
  <div class="w3-display-middle w3-center">
    <span class="w3-text-white" style="font-size:90px">the<br>Detroit Mural Project</span>
  </div>
  <div class="w3-display-bottomright w3-center w3-padding-large">
    <span class="w3-text-white">${mural.name }</span>
  </div>
</header>
<p id="demo"> </p>


<a href = "https://www.google.com/maps/dir//${mural.address }" target="_blank">${mural.address }</a><br>
<%-- <div style="font-family:courier;">Artist: ${mural.artistname }</div>
 --%>

<!-- Add a background color and large text to the whole page 
<div class="w3-sand w3-grayscale w3-large">-->


<!-- About Container -->
<div class="w3-container" id="about"> 
  <div class="w3-content" style="max-width:800px">
    <h5 class="w3-center w3-padding-64"><span class="w3-tag w3-wide">ABOUT THE APP</span></h5>
       <p style="font-size:20px" ALIGN="center">We aim to provide a comprehensive collection of all the murals in Detroit.
        Users can log in to save their favorites and get crowd-sourced recommendations based 
        on the favorites they choose. </p>
    <div class="w3-panel w3-leftbar w3-light-grey">
      <p><i>"Art is meant to be shared"</i></p>
     </div>
   
  </div>
</div>



<!-- Footer -->
<!-- <footer class="w3-center w3-light-grey w3-padding-48 w3-large">
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-text-green">w3.css</a></p>
</footer> -->

<script>
// Tabbed Menu
var z24 = 0; 
var z25 = 0; 
function openMenu(evt, menuName) {
  var i, x, tablinks;
  x = document.getElementsByClassName("menu");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" w3-dark-grey", "");
  }
  document.getElementById(menuName).style.display = "block";
  evt.currentTarget.firstElementChild.className += " w3-dark-grey";
}
//document.getElementById("myLink").click();



function getLocation() {
  if (navigator.geolocation) {
	  navigator.geolocation.getCurrentPosition(setPosition);
    return true
  } else {
    z24.innerHTML = "Geolocation is not supported by this browser.";
    return false
  }
} 


function setPosition(position) {
	z24 = position.coords.latitude;
	z25 = position.coords.longitude; 
}

//needed because a separate function is needed to change the values from submitting the form
//due to the immediately of going to the controller. 
function submitCheckInForm() {
	fz24 = document.getElementsByName("lattitude")[0];
	fz25 = document.getElementsByName("longitude")[0];
	fz24.value = z24;
	fz25.value = z25; 
	alert(fz24.value + " " +  fz25.value + "")
	document.getElementById("checkin").submit(); 
}


</script>

</body>
</html>
    
