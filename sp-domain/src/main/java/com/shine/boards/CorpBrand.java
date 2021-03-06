package com.shine.boards;

import com.shine.objects.ShineObject;

public class CorpBrand extends ShineObject{

	private static final long serialVersionUID = 1L;
	private int id, corporateId;
	private String backgroundimg, url, name, linkColour, textColour, logo, bgColour;
	
	public CorpBrand() {
		super ();
	}
	
	public CorpBrand(int id, int id2, String name) {
		super(id);
		id = id2;
		this.name = name;
	}
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
	
	
}
