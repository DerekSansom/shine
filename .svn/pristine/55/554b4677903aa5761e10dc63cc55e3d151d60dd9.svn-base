package com.shine.error;

import com.shine.ShineError;

public enum RegistrationError implements ShineError {

	USERNAME_REQUIRED(-10),
	PHONE_INVALID(-17),
	EMAIL_INVALID(-13),
	EMAIL_REQUIRED(-14),
	USERNAME_TAKEN(-11),
	USERNAME_4_CHARS_REQD(-12),
	EMAIL_TAKEN(-15), FULLNAME_REQUIRED(-16), USERNAME_TOO_LONG(-18), FULLNAME_TOO_LONG(-19), PASSWORD_REQUIRED(-20), PASSWORD_LENGTH(-21);

	private RegistrationError(int code) {
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
