package com.sp.entity.savedads;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserSavedAdId implements Serializable {

	public UserSavedAdId() {
	}

	UserSavedAdId(int userId, int adId) {
		this.userId = userId;
		this.adId = adId;
	}

	@Column(name = "userid")
	private int userId;

	@Column(name = "adid")
	private int adId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserSavedAdId) {
			UserSavedAdId other = (UserSavedAdId) obj;
			return userId == other.userId && adId == other.adId;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return userId * adId * 57;
	}
}