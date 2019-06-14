package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	private String username; 
	private String password; 
	private ArrayList<Integer> muralids; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userid;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, ArrayList<Integer> muralids, Integer userid) {
		super();
		this.username = username;
		this.password = password;
		this.muralids = muralids;
		this.userid = userid;
	}
	
	public User(String username, String password, ArrayList<Integer> muralids) {
		super();
		this.username = username;
		this.password = password;
		this.muralids = muralids;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Integer> getMuralids() {
		return muralids;
	}

	public void setMuralids(ArrayList<Integer> muralids) {
		this.muralids = muralids;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	} 
	
	

}
