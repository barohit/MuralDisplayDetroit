<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Art near me</title>

<script>
var map;
var markersOnMap=[];
var InforObj=[];
var centerCords = {
	lat:42.341,
	lng:-83.045
};

function initMarkers(){
	/* add each mural from database to list */
 	<c:forEach items="${murals}" var="m">
 	
 		markersOnMap.push({name:"${m.name}",LatLng:[{lat:${m.latitude},lng:${m.longitude}}],
 				image:"${m.imgloc}", artist:"${m.artistname}", id:${m.muralid}});
	</c:forEach>	
	
	} 

//initialize map 
window.onload = function(){
	initMap();
};

//adds markers from list to map
function addMarkerInfo(){
	for (var i = 0; i < markersOnMap.length; i++){
		
		var contentStr ="<div><img src="+markersOnMap[i].image+" style ='height:175px'></div><br>"+ markersOnMap[i].name;
		const marker = new google.maps.Marker({
			position: markersOnMap[i].LatLng[0],
			map: map
		});
		const infowindow = new google.maps.InfoWindow({
			content: contentStr
		});

		marker.addListener('click', function (){
			closeOtherInfo();
			infowindow.open(marker.get('map'), marker);
			InforObj[0] = infowindow;
			map.setZoom(15);
	   		map.setCenter(marker.getPosition());
		});	
		//opens info window on mouseover
		     marker.addListener('mouseover', function () {
                    closeOtherInfo();
                    infowindow.open(marker.get('map'), marker);
                    InforObj[0] = infowindow;
                });
		     	//this code closes the window when the mouse isnt hovering over
                // marker.addListener('mouseout', function () {
                //     closeOtherInfo();
                //     infowindow.close();
                //     InforObj[0] = infowindow;
                // });
	}

}
//only 1 infowindow open at a time
function closeOtherInfo(){
	if (InforObj.length>0){
		  /* detach the info-window from the marker ... undocumented in the API docs */
        InforObj[0].set("marker", null);
                /* and close it */
        InforObj[0].close();
                /* blank the array */
        InforObj.length = 0;
		}
	}

//initialize map
function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		zoom:12,
		center: centerCords
	});
	initMarkers();
	addMarkerInfo();
}

</script>

</head>
<body>
<h1>Murals in Detroit</h1>


<div id="map" style="width:100%;height:420px;"></div>

<script src="https://maps.googleapis.com/maps/api/js?key=${mapkey}"></script>
<font size="1">by Rohit Baddam, Kuruvilla Bose & Nick TenBrink</font>

</body>
</html>