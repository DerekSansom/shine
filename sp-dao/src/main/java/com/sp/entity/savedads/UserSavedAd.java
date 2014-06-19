package com.sp.entity.savedads;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_saved_ads")
public class UserSavedAd implements Serializable {

	public UserSavedAd() {
	}

	public UserSavedAd(int userId, int adId) {
		this.id = new UserSavedAdId(userId, adId);
	}

	@EmbeddedId
	private UserSavedAdId id;

	public UserSavedAdId getId() {
		return id;
	}

	public void setId(UserSavedAdId id) {
		this.id = id;
	}
}
