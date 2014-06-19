package com.sp.exception;

import com.shine.ShineError;

public class SpDaoException extends RuntimeException {

	private ShineError errorCode;

	public SpDaoException(ShineError errorCode) {
		this.errorCode = errorCode;
	}

	public SpDaoException(ShineError errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public SpDaoException(ShineError errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public SpDaoException(ShineError errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

}
