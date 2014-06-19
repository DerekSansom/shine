package com.sp.entity.ad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sp.entity.CorpBrandEntity;

@Entity
@Table(name = "adverts")
public class AdvertEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "displayname")
	private String displayname;

	@Column(name = "title")
	private String title;

	@Column(name = "profile")
	private String profile;

	@Column(name = "text")
	private String text;

	@Column(name = "logo")
	private String iconUrl;

	@Column(name = "image")
	private String imageUrl;

	@Column(name = "phoneno")
	private String phoneno;

	@Column(name = "email")
	private String email;

	@Column(name = "weburl")
	private String url;

	@Column(name = "created")
	private Date created;

	@Column(name = "expires")
	private Date expires;

	@Column(name = "catid")
	private int categoryId;

	@Column(name = "lng")
	private Double lng;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "defaultbin")
	private boolean defaultbin;

	@Column(name = "post_facebook")
	private boolean facebook;

	@Column(name = "post_twitter")
	private boolean twitter;

	@Column(name = "userId")
	private Integer userId;

	@Column(name = "advertiserId")
	private Integer advertiserId;

	@ManyToOne
	@JoinColumn(name = "brandid")
	// @LazyCollection(LazyCollectionOption.FALSE)
	private CorpBrandEntity brand;

//	@Transient
//	private String category;

	public AdvertEntity() {

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

//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}

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
		if (o instanceof AdvertEntity) {
			return ((AdvertEntity) o).id == id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CorpBrandEntity getBrand() {
		return brand;
	}

	public void setBrand(CorpBrandEntity brand) {
		this.brand = brand;
	}

}
