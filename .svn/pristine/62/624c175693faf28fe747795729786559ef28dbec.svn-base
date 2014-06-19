package com.shine;

public enum GeneralError implements ShineError {

	SYSTEM_ERROR(-99),
	PARAM_MISSING(-98),
	LOGGED_OUT(-97),
	NOT_FOUND(-49),
	SUSPENDED(-48),
	PLAYER_NOT_FOUND(-39), ERROR_MAX_IMG_SIZE(-51);

	GeneralError(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return name();
	}

}
