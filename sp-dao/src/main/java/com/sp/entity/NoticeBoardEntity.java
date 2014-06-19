package com.sp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shine.boards.BoardCategory;

@Entity
@Table(name = "noticeboards")
public class NoticeBoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String locationName;

	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private BoardCategory category;

	@Column(name = "bannerimg")
	private String bannerAd;

	@Column(name = "bannerurl")
	private String bannerUrl;

	@Column(name = "feedurl")
	private String rss1;

	@Column(name = "feedurl2")
	private String rss2;

	@Column(name = "feedtitle")
	private String rss1Title;

	@Column(name = "feedtitle2")
	private String rss2Title;

	@Column(name = "creatorid")
	private Integer creatorId;

	@ManyToOne
	@JoinColumn(name = "brandid")
	private CorpBrandEntity brand;

	@ManyToOne
	@JoinColumn(name = "playerid")
	private PlayerEntity player;

	private Boolean active;

	@Column(name = "created")
	private Date created;

	@Column(name = "suspended")
	private Date suspended;

	@Column(name = "lastupdate")
	private Date lastUpdate;

	@Column(name = "lat")
	private double lat;

	@Column(name = "lng")
	private double lng;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public CorpBrandEntity getBrand() {
		return brand;
	}

	public void setBrand(CorpBrandEntity brand) {
		this.brand = brand;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getRss1() {
		return rss1;
	}

	public void setRss1(String rss1) {
		this.rss1 = rss1;
	}

	public String getRss2() {
		return rss2;
	}

	public void setRss2(String rss2) {
		this.rss2 = rss2;
	}

	public String getBannerAd() {
		return bannerAd;
	}

	public void setBannerAd(String bannerAd) {
		this.bannerAd = bannerAd;
	}

	public String getRss1Title() {
		return rss1Title;
	}

	public void setRss1Title(String rss1Title) {
		this.rss1Title = rss1Title;
	}

	public String getRss2Title() {
		return rss2Title;
	}

	public void setRss2Title(String rss2Title) {
		this.rss2Title = rss2Title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NoticeBoardEntity) {
			return id == ((NoticeBoardEntity) o).getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 27 * id;
	}

	public Date getSuspended() {
		return suspended;
	}

	public void setSuspended(Date suspended) {
		this.suspended = suspended;
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public void setCategory(BoardCategory category) {
		this.category = category;
	}

	public BoardCategory getCategory() {
		return category;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
