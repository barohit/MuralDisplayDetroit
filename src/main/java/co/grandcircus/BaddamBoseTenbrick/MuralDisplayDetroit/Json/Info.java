package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Just a json object from the request endpoint
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
	
	Geometry geometry;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	
	
	

}
