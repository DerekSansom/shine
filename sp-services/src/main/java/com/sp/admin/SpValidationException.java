package com.sp.admin;

import java.util.List;

public class SpValidationException extends RuntimeException {

	private List<String> messages;

	public SpValidationException(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
