package com.sp.admin;

import java.util.List;

import com.sp.entity.CorpBrandEntity;

public class BoardOwnershipForm {

	private Integer boardId;
	private Integer ownerId;
	private String newOwnerName;
	private String boardName;
	private String previousOwnerName;
	private List<CorpBrandEntity> newOwnerBrands;
	private Integer newBrand;
	private String newBrandName;

	public String getNewOwnerName() {
		return newOwnerName;
	}

	public void setNewOwnerName(String newOwnerName) {
		this.newOwnerName = newOwnerName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getPreviousOwnerName() {
		return previousOwnerName;
	}

	public void setPreviousOwnerName(String previousOwnerName) {
		this.previousOwnerName = previousOwnerName;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public void setNewOwnerBrands(List<CorpBrandEntity> newOwnerBrands) {
		this.newOwnerBrands = newOwnerBrands;

	}

	public List<CorpBrandEntity> getNewOwnerBrands() {
		return newOwnerBrands;
	}

	public Integer getNewBrand() {
		return newBrand;
	}

	public void setNewBrand(Integer newBrand) {
		this.newBrand = newBrand;
	}

	public String getNewBrandName() {
		return newBrandName;
	}

	public void setNewBrandName(String newBrandName) {
		this.newBrandName = newBrandName;
	}


}
