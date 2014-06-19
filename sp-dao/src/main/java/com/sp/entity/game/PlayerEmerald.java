package com.sp.entity.game;

public class PlayerEmerald extends PlayerStone {
	private static final long serialVersionUID = -5116741351054111498L;

	@Override
	public int hashCode() {
		final int prime = 43;
		int result = 1;
		result = prime * result + stoneId;
		result = prime * result + userId;
		return result;
	}

}
