package com.sp.entity.ad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "default_ad_params")
public class DefaultAdParams implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// @Column(name = "adId")
	// private int adId;

	@ManyToOne
	@JoinColumn(name = "adId")
	private AdvertEntity advert;

	@Column(name = "boardId")
	private Integer boardId;
	@Column(name = "countryId")
	private Integer countryId;
	@Column(name = "area1Id")
	private Integer area1Id;
	@Column(name = "area2Id")
	private Integer area2Id;
	@Column(name = "area3Id")
	private Integer area3Id;

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdId() {
		return advert.getId();
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

	public AdvertEntity getAdvert() {
		return advert;
	}

	public void setAdvert(AdvertEntity advert) {
		this.advert = advert;
	}

	@Override
	public String toString() {
		return "DefaultAdParams [advert=" + advert.getId() +
				boardId != null ? ", boardId=" + boardId : "" +
				countryId != null ? ", countryId=" + countryId : "" +
				area1Id != null ? ", area1Id=" + area1Id : "" +
				area2Id != null ? ", area2Id=" + area2Id : "" +
				area3Id != null ? ", area3Id=" + area3Id : "" +
				"]";
	}

}
