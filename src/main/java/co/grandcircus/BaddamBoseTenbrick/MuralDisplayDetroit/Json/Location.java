package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Just a json object from the request endpoint
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	
	Double lat;  
	Double lng;
	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	} 
	
	

}
