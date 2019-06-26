package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	
	Info[] results;

	public Info[] getResults() {
		return results;
	}

	public void setResults(Info[] results) {
		this.results = results;
	} 
	
	

}
