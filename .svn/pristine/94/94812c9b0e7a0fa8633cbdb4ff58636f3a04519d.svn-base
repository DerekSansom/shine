package com.sp.entity.game;

import java.io.Serializable;

public abstract class PlayerStone implements Serializable {
	private static final long serialVersionUID = -6341443391428984840L;
	protected int userId;
	protected int stoneId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStoneId() {
		return stoneId;
	}

	public void setStoneId(int stoneId) {
		this.stoneId = stoneId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerStone other = (PlayerStone) obj;
		if (stoneId != other.stoneId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
