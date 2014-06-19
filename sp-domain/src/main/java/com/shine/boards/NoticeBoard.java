package com.shine.boards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.shine.objects.LocObject;
import com.shine.objects.OtherPlayer;

@JsonRootName("noticeboard")
public class NoticeBoard extends LocObject implements Serializable
{

	private static final long serialVersionUID = 1L;
	private List<Notice> notices = new ArrayList<Notice>();
	private List<Advert> ads = new ArrayList<Advert>();
	private String name, locName, creator, bannerAd, bannerUrl, rss1, rss2, rss1Title, rss2Title;
	private BoardCategory category;
	private int noticeCount = -1, advertCount = -1, userCount = -1;
	private CorpBrand brand;
	private Integer creatorId, brandId;
	private Boolean active, available;

	private Date suspended;
	private Map<Integer, OtherPlayer> activeUsers;

	public NoticeBoard(int id, String name, String locName, Integer creatorId) {
		super(id);
		this.name = name;
		this.locName = locName;
		this.creatorId = creatorId;
	}

	public NoticeBoard() {

	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public void addNotice(Notice notice) {
		this.notices.add(0, notice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Advert> getAds() {
		return ads;
	}

	public void setAds(List<Advert> ads) {
		this.ads = ads;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public int getAdvertCount() {
		return advertCount;
	}

	public void setAdvertCount(int advertCount) {
		this.advertCount = advertCount;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getLocationName() {
		return locName;
	}

	public void setLocationName(String locName) {
		this.locName = locName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public CorpBrand getBrand() {
		return brand;
	}

	public void setBrand(CorpBrand brand) {
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

	public Map<Integer, OtherPlayer> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(Map<Integer, OtherPlayer> activeUsers) {
		this.activeUsers = activeUsers;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NoticeBoard) {
			return id == ((NoticeBoard) o).getId();
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

	public BoardCategory getCategory() {
		return category;
	}

	public void setCategory(BoardCategory category) {
		this.category = category;
	}
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
