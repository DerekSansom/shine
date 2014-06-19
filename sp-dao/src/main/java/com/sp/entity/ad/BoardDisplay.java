package com.sp.entity.ad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "display_params_board")
public class BoardDisplay implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "display_id")
	private int displayId;

	@Column(name = "board_id")
	private Integer boardId;
	@Column(name = "countryId")
	private Integer countryId;
	@Column(name = "area1id")
	private Integer area1Id;
	@Column(name = "area2id")
	private Integer area2Id;
	@Column(name = "area3id")
	private Integer area3Id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisplayId() {
		return displayId;
	}

	public void setDisplayId(int displayId) {
		this.displayId = displayId;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
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
