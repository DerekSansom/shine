package com.sp.entity.track;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trk_ad_serves")
public class AdServe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "viewid")
	private int viewId;

	@Column(name = "adid")
	private int adId;

	private int position;

	public AdServe() {
	}

	public AdServe(int viewId, int adId, int position) {
		super();
		this.viewId = viewId;
		this.adId = adId;
		this.position = position;
	}

	public int getViewId() {
		return viewId;
	}

	public int getAdId() {
		return adId;
	}

	public int getPosition() {
		return position;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
