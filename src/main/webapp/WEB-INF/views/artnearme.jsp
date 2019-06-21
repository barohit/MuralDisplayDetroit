<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Art near me</title>
<script src="markerclusterer.js"  type="text/javascript" ></script>
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

var markers=[];
//adds markers from list to map
function addMarkerInfo(){
	for (var i = 0; i < markersOnMap.length; i++){
		
		var contentStr ="<div><img src="+markersOnMap[i].image+" style ='height:175px'></div><br>"
		+ markersOnMap[i].name+"<br>"+markersOnMap[i].artist;
		const marker = new google.maps.Marker({
			position: markersOnMap[i].LatLng[0],
			map: map
		});
		const infowindow = new google.maps.InfoWindow({
			content: contentStr
		});
		
		markers.push(marker);

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
      	/* 	marker.addListener('mouseout', function () {
            	closeOtherInfo();
               	infowindow.close();
                InforObj[0] = infowindow;
            });   */
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
		center: centerCords,
		mapTypeId: 'satellite' 
	});
	initMarkers();
	addMarkerInfo();
	
	var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'images/m',
		maxZoom:16});	
}
var customStyled = [
	  {
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#ebe3cd"
		      }
		    ]
		  },
		  {
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#523735"
		      }
		    ]
		  },
		  {
		    "elementType": "labels.text.stroke",
		    "stylers": [
		      {
		        "color": "#f5f1e6"
		      }
		    ]
		  },
		  {
		    "featureType": "administrative",
		    "elementType": "geometry.stroke",
		    "stylers": [
		      {
		        "color": "#c9b2a6"
		      }
		    ]
		  },
		  {
		    "featureType": "administrative.land_parcel",
		    "elementType": "geometry.stroke",
		    "stylers": [
		      {
		        "color": "#dcd2be"
		      }
		    ]
		  },
		  {
		    "featureType": "administrative.land_parcel",
		    "elementType": "labels",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "administrative.land_parcel",
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#ae9e90"
		      }
		    ]
		  },
		  {
		    "featureType": "landscape.natural",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#dfd2ae"
		      }
		    ]
		  },
		  {
		    "featureType": "poi",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#dfd2ae"
		      }
		    ]
		  },
		  {
		    "featureType": "poi",
		    "elementType": "labels.text",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "poi",
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#93817c"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.business",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.business",
		    "elementType": "labels.text",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.government",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.medical",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.park",
		    "elementType": "geometry.fill",
		    "stylers": [
		      {
		        "color": "#a5b076"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.park",
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#447530"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.school",
		    "elementType": "geometry.fill",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "poi.sports_complex",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "road",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#f5f1e6"
		      }
		    ]
		  },
		  {
		    "featureType": "road.arterial",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#fdfcf8"
		      }
		    ]
		  },
		  {
		    "featureType": "road.highway",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#f8c967"
		      }
		    ]
		  },
		  {
		    "featureType": "road.highway",
		    "elementType": "geometry.stroke",
		    "stylers": [
		      {
		        "color": "#e9bc62"
		      }
		    ]
		  },
		  {
		    "featureType": "road.highway.controlled_access",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#e98d58"
		      }
		    ]
		  },
		  {
		    "featureType": "road.highway.controlled_access",
		    "elementType": "geometry.stroke",
		    "stylers": [
		      {
		        "color": "#db8555"
		      }
		    ]
		  },
		  {
		    "featureType": "road.local",
		    "elementType": "labels",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "road.local",
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#806b63"
		      }
		    ]
		  },
		  {
		    "featureType": "transit.line",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#dfd2ae"
		      }
		    ]
		  },
		  {
		    "featureType": "transit.line",
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#8f7d77"
		      }
		    ]
		  },
		  {
		    "featureType": "transit.line",
		    "elementType": "labels.text.stroke",
		    "stylers": [
		      {
		        "color": "#ebe3cd"
		      }
		    ]
		  },
		  {
		    "featureType": "transit.station",
		    "stylers": [
		      {
		        "visibility": "off"
		      }
		    ]
		  },
		  {
		    "featureType": "transit.station",
		    "elementType": "geometry",
		    "stylers": [
		      {
		        "color": "#dfd2ae"
		      }
		    ]
		  },
		  {
		    "featureType": "water",
		    "elementType": "geometry.fill",
		    "stylers": [
		      {
		        "color": "#b9d3c2"
		      }
		    ]
		  },
		  {
		    "featureType": "water",
		    "elementType": "labels.text.fill",
		    "stylers": [
		      {
		        "color": "#92998d"
		      }
		    ]
		  }
		]
		
map.set('styles',customStyled); 

</script>

</head>
<body>
<h1>Murals in Detroit</h1>


<div id="map" style="width:100%;height:420px;"></div>
<!--  <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
    </script> -->
<script src="https://maps.googleapis.com/maps/api/js?key=${mapkey}"></script>
<br>


<a href = "/">go back to homepage</a>
<br>
<font size="1">by Rohit Baddam, Kuruvilla Bose & Nick TenBrink</font>

</body>
</html>