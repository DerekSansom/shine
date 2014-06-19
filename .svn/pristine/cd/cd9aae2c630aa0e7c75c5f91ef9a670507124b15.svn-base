package com.sp.auth;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

	private static final String chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-";
	private SecureRandom secureRandom = new SecureRandom();

	public String generateToken() {

		char[] chars = new char[32];

		for (int i = 0; i < chars.length; i++) {
			chars[i] = getChar();
		}
		return new String(chars);
	}

	private char getChar() {

		return chars.charAt(secureRandom.nextInt(chars.length()));
	}


}
