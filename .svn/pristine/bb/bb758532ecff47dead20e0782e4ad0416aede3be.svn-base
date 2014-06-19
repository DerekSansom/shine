package com.sp.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Auth failed")
public class AuthFailedException extends RuntimeException {

	public AuthFailedException(String message) {
		super(message);
	}

}
