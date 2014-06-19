package com.sp.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Table(name = "player_loc")
public class PlayerLocEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private int userid;
	private double lat;
	private double lng;

	public PlayerLocEntity() {
	}

	public int getUserid() {
		return userid;
	}

	public PlayerLocEntity(int userid, double lat, double lng) {
		this.userid = userid;
		this.lat = lat;
		this.lng = lng;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}
