package com.sp.admin;

import java.util.List;

import com.sp.entity.CorpBrandEntity;

public class BoardsAdminOwnershipRequest {

	private Integer boardId;
	private Integer newOwnerId;
	private Integer adminId;
	private String newOwnerName;
	private String boardName;
	private String previousOwnerName;
	private List<CorpBrandEntity> newOwnerBrands;

	public BoardsAdminOwnershipRequest(Integer boardId, Integer newOwnerId, Integer adminId) {
		this.boardId = boardId;
		this.newOwnerId = newOwnerId;
		this.adminId = adminId;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public Integer getNewOwnerId() {
		return newOwnerId;
	}

	public void setNewOwnerId(Integer newOwnerId) {
		this.newOwnerId = newOwnerId;
	}

	public String getNewOwnerName() {
		return newOwnerName;
	}

	public void setNewOwnerName(String newOwnerName) {
		this.newOwnerName = newOwnerName;
	}

	public String getPreviousOwnerName() {
		return previousOwnerName;
	}

	public void setPreviousOwnerName(String previousOwnerName) {
		this.previousOwnerName = previousOwnerName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public List<CorpBrandEntity> getNewOwnerBrands() {
		return newOwnerBrands;
	}

	public void setNewOwnerBrands(List<CorpBrandEntity> newOwnerBrands) {
		this.newOwnerBrands = newOwnerBrands;
	}

}
