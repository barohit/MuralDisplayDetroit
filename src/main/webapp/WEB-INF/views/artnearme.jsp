<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Art near me</title>

<script>
var map;
var InforObj=[];
var centerCords = {
	lat:42.341,
	lng:-83.045
};

var markersOnMap =[{
	name: "#WhatLiftsYou Mural",
	LatLng:[{lat:42.3356154,
		lng:-83.0497705
	}],
	image: "https://i.imgur.com/mjJSRfI.jpg style = 'width: 280px; height: 150px'"
},
{name: "Whale Mural @ Broderick Towers",
	LatLng:[{
		lat:42.3360824,
		lng:-83.0498275
	}],
	image: "https://www.wyland.com/media/20110209104734%20.jpg style = 'width:150px; height:200px'"

	},
	{name:"Pat Perry Mural",
	LatLng:[{
		lat:42.3274354,
		lng:-83.079290
	}],
	image:"http://patperry.net/cache/images/local/uploads/projects/sw_mural_patperryPanorama2_sa_3193_1000_40.jpg style = 'width:500px; height:150px'"
},
	{name: "Bernal Perez life of pi mural",
	LatLng:[{
		lat:42.3155905,
		lng:-83.1139593
	}],
	image:"https://www.modeldmedia.com/Images/Features/issue610/1.jpg style = 'width:350px; height:250px'"

},
	{name:"ellen rutt cubism mural",
	LatLng:[{
		lat:42.3338953,
		lng:-83.0992146 
	}],
	image:"https://www.modeldmedia.com/Images/Features/issue610/2.jpg style = 'width:350px; height:300px'"
}

];

window.onload = function(){
	initMap();
};

function addMarkerInfo(){
	for (var i = 0; i < markersOnMap.length; i++){
		
		var contentStr ="<div><img src="+markersOnMap[i].image+"></div><br>"+ markersOnMap[i].name;
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


function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		zoom:12,
		center: centerCords
	});
	addMarkerInfo();
}

</script>

</head>
<body>
<h1>Murals in Detroit</h1>


<div id="map" style="width:100%;height:420px;"></div>

<script src="https://maps.googleapis.com/maps/api/js?key=${mapkey}&callback=myMap"></script>
<font size="1">by Rohit Baddam, Kuruvilla Bose & Nick TenBrink</font>

</body>
</html>