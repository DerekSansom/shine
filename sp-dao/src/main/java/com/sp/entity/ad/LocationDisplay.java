package com.sp.entity.ad;

import java.io.Serializable;

import javax.persistence.Column;

public class LocationDisplay implements Serializable {

	@Column(name = "id")
	private int displayId;
	@Column(name = "id")
	private Integer countryId;
	@Column(name = "id")
	private Integer area1Id;
	@Column(name = "id")
	private Integer area2Id;
	@Column(name = "id")
	private Integer area3Id;

	public int getDisplayId() {
		return displayId;
	}

	public void setDisplayId(int displayId) {
		this.displayId = displayId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getArea1Id() {
		return area1Id;
	}

	public void setArea1Id(Integer area1Id) {
		this.area1Id = area1Id;
	}

	public Integer getArea2Id() {
		return area2Id;
	}

	public void setArea2Id(Integer area2Id) {
		this.area2Id = area2Id;
	}

	public Integer getArea3Id() {
		return area3Id;
	}

	public void setArea3Id(Integer area3Id) {
		this.area3Id = area3Id;
	}

}
