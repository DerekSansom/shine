package com.shine.error;

import com.shine.ShineError;

public enum AuthError implements ShineError {

	LOG_IN_FAILED(-20);

	private AuthError(int code) {
		this.code = code;
	}

	private int code;

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return name();
	}

}
