package com.sp.entity.ad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExcludedCategoryPK implements Serializable
{
	@Column(name = "catid")
	private int catId;

	@Column(name = "boardid")
	private int boardId;

	public int getCatId() {
		return catId;
	}

	void setCatId(int catId) {
		this.catId = catId;
	}

	int getBoardId() {
		return boardId;
	}

	void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardId;
		result = prime * result + catId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcludedCategoryPK other = (ExcludedCategoryPK) obj;
		if (boardId != other.boardId)
			return false;
		if (catId != other.catId)
			return false;
		return true;
	}

}
