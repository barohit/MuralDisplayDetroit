package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favoriteid")
	private Integer id;
	private Integer muralid;
	private Integer userid;

	public Favorite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Favorite(Integer muralid) {
		super();
		this.muralid = muralid;
	}

	public Favorite(Integer muralid, Integer userid) {
		super();
		this.muralid = muralid;
		this.userid = userid;
	}

	public Favorite(Integer id, Integer muralid, Integer userid) {
		super();
		this.id = id;
		this.muralid = muralid;
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
