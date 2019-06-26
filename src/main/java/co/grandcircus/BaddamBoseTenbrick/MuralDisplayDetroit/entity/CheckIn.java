package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="checkins")
public class CheckIn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer checkinid;
	Integer muralid; 
	Integer userid;
	
	
	public CheckIn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CheckIn(Integer muralid) {
		super();
		this.muralid = muralid;
	}
	
	public CheckIn(Integer muralid, Integer userid) {
		super();
		this.muralid = muralid;
		this.userid = userid;
	}


	public CheckIn(Integer checkinid, Integer muralid, Integer userid) {
		super();
		this.checkinid = checkinid;
		this.muralid = muralid;
		this.userid = userid;
	}


	public Integer getCheckinid() {
		return checkinid;
	}
	public void setCheckinid(Integer checkinid) {
		this.checkinid = checkinid;
	}
	public Integer getMuralid() {
		return muralid;
	}
	public void setMuralid(Integer muralid) {
		this.muralid = muralid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	} 
	
	

}
