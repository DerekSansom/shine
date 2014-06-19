package com.sp.entity.track;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trk_ad_views")
public class AdView extends TrackObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private int userId;

	@Column(name = "adid")
	private int adId;

	public AdView() {
		super(0);
	}

	public AdView(int userId, int adId, int client) {
		super(client);
		this.userId = userId;
		this.adId = adId;
	}

	public int getUserId() {
		return userId;
	}

	public int getAdId() {
		return adId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
