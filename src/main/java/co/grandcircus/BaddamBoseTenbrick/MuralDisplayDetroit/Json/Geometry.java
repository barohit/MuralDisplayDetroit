package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Just a json object from the request endpoint
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

	Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
