package com.sp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "corp_brands")
public class CorpBrandEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "corpid")
	private int corporateId;

	@Column(name = "background")
	private String backgroundimg;

	@Column(name = "url")
	private String url;

	@Column(name = "name")
	private String name;

	@Column(name = "linkColour")
	private String linkColour;

	@Column(name = "textColour")
	private String textColour;

	@Column(name = "logo")
	private String logo;

	@Column(name = "bgColour")
	private String bgColour;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(int corporateId) {
		this.corporateId = corporateId;
	}

	public String getBackgroundimg() {
		return backgroundimg;
	}

	public void setBackgroundimg(String backgroundimg) {
		this.backgroundimg = backgroundimg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkColour() {
		return linkColour;
	}

	public void setLinkColour(String linkColour) {
		this.linkColour = linkColour;
	}

	public String getTextColour() {
		return textColour;
	}

	public void setTextColour(String textColour) {
		this.textColour = textColour;
	}

	public String getBgColour() {
		return bgColour;
	}

	public void setBgColour(String bgColour) {
		this.bgColour = bgColour;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "CorpBrandEntity [id=" + id + ", corporateId=" + corporateId
				+ ", backgroundimg=" + backgroundimg + ", url=" + url
				+ ", name=" + name + ", linkColour=" + linkColour
				+ ", textColour=" + textColour + ", logo=" + logo
				+ ", bgColour=" + bgColour + "]";
	}

}
