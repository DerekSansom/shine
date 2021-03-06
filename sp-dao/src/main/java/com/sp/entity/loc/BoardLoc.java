package com.sp.entity.loc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loc_board")
public class BoardLoc {

	@Id
	@Column(name = "boardId")
	private int boardId;
	@Column(name = "countryid")
	private int countryId;
	@Column(name = "area1id")
	private Integer area1Id;
	@Column(name = "area2id")
	private Integer area2Id;
	@Column(name = "area3id")
	private Integer area3Id;
	@Column(name = "interventionreqd")
	private boolean interventionReqd;

	@Override
	public String toString() {

		return "boardid:" + boardId + " countryid:" + countryId + " area1id:" + area1Id + " area2id:" + area2Id
				+ " area3id:" + area3Id;
	}

	public boolean isInterventionReqd() {
		return interventionReqd;
	}

	public void setInterventionReqd(boolean interventionReqd) {
		this.interventionReqd = interventionReqd;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
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
