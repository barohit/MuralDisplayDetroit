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
	private String muralids; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userid;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String muralids, Integer userid) {
		super();
		this.username = username;
		this.password = password;
		this.muralids = muralids;
		this.userid = userid;
	}
	
	public User(String username, String password, String muralids) {
		super();
		this.username = username;
		this.password = password;
		this.muralids = muralids;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	public String getMuralids() {
		return muralids;
	}

	public void setMuralids(String muralids) {
		this.muralids = muralids;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	} 
	
	

}
