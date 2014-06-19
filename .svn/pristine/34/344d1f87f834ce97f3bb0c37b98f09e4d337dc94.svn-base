package com.shine.boards;

import java.io.Serializable;
import java.util.Date;

import com.shine.objects.ShineObject;

public class Advert extends ShineObject implements Serializable {

	// private static final long serialVersionUID = -2746242160804017500L;
	private String displayname, title, profile, text, iconUrl, imageUrl, category, phoneno, email, url;
	private Date created, expires;
	private int categoryId;
	private Double lat, lng;
	private boolean defaultbin, facebook, twitter;
	private Integer userId, advertiserId;
	private CorpBrand brand;

	public Advert() {

	}

	// public Advert(int id, String displayname, String title, String text,
	// String iconUrl, String imageUrl, String category, String phoneno,
	// String email, Date created, Date expires, int categoryId, int creatorId,
	// double lat, double lng) {
	// this(id, displayname, title, text, iconUrl, imageUrl, category, phoneno,
	// email, expires, categoryId, creatorId, lat, lng);
	// this.created = created;
	// }
	// public Advert(int id, String displayname, String title, String text,
	// String iconUrl, String imageUrl, String category, String phoneno,
	// String email, Date expires, int categoryId, int advertiserId,
	// double lat, double lng) {
	// super(id);
	// this.displayname = displayname;
	// this.title = title;
	// this.text = text;
	// this.iconUrl = iconUrl;
	// this.imageUrl = imageUrl;
	// this.category = category;
	// this.phoneno = phoneno;
	// this.email = email;
	// this.expires = expires;
	// this.categoryId = categoryId;
	// this.advertiserId = advertiserId;
	// this.lat = lat;
	// this.lng = lng;
	// }

	public Advert(String title, String text, String phoneno, String email, Date expires, int categoryId,
			int userId, Double lat, Double lng) {
		this.title = title;
		this.text = text;
		this.phoneno = phoneno;
		this.email = email;
		this.expires = expires;
		this.categoryId = categoryId;
		this.userId = userId;
		this.lat = lat;
		this.lng = lng;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public boolean isDefaultbin() {
		return defaultbin;
	}

	public void setDefaultbin(boolean defaultbin) {
		this.defaultbin = defaultbin;
	}

	public boolean isFacebook() {
		return facebook;
	}

	public void setFacebook(boolean facebook) {
		this.facebook = facebook;
	}

	public boolean isTwitter() {
		return twitter;
	}

	public void setTwitter(boolean twitter) {
		this.twitter = twitter;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Advert) {
			return ((Advert) o).id == id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 93 * id;
	}

	@Override
	public String toString() {
		return "Ad: " + id + ", " + displayname + ", catid:" + categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Integer advertiserId) {
		this.advertiserId = advertiserId;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public Double getLng() {
		return lng;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CorpBrand getBrand() {
		return brand;
	}

	public void setBrand(CorpBrand brand) {
		this.brand = brand;
	}

}
