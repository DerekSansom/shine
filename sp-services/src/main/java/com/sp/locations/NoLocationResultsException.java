package com.sp.locations;

public class NoLocationResultsException extends RuntimeException {
	public NoLocationResultsException(String message) {
		super(message);
	}
}
