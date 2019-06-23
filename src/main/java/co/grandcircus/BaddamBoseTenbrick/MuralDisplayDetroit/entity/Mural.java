package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="murals")
public class Mural {
	
	private String imgloc; 
	private Double latitude; 
	private Double longitude; 
	private String address; 
	private String neighborhood; 
	private String tags; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer muralid; 
	private String name; 
	private String artistname; 
	
	public Mural() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mural(String imgloc, Double latitude, Double longitude, String address, String neighborhood,
			String tags, Integer muralid, String name, String artistname) {
		super();
		this.imgloc = imgloc;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.neighborhood = neighborhood;
		this.tags = tags;
		this.muralid = muralid;
		this.name = name;
		this.artistname = artistname;
	}
	
	//no id constructor
	public Mural(String imgloc, Double latitude, Double longitude, String address, String neighborhood,
			String tags, String name, String artistname) {
		super();
		this.imgloc = imgloc;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.neighborhood = neighborhood;
		this.tags = tags;
		this.name = name;
		this.artistname = artistname;
	}
	
	public Mural(String imgloc, Double latitude, Double longitude, String address, String neighborhood,
			 String name, String artistname) {
		super();
		this.imgloc = imgloc;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.neighborhood = neighborhood;
		this.name = name;
		this.artistname = artistname;
	}

	public String getImgloc() {
		return imgloc;
	}

	public void setImgloc(String imgloc) {
		this.imgloc = imgloc;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getMuralid() {
		return muralid;
	}

	public void setMuralid(Integer muralid) {
		this.muralid = muralid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistname() {
		return artistname;
	}

	public void setArtistname(String artistname) {
		this.artistname = artistname;
	}

	
	

}
