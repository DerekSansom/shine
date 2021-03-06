package com.sp.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

	public static final String SALT = "sp-45-skm";
	//TODO: set this to true when it is possible to reset password from email link
	private static final boolean encryptPassword = true;

	public String hashPassword(String salt, String password) {
		if (!encryptPassword) {
			return password;
		}

		String saltedPassword = salt + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;
	}

	public String hash(String salt, String password) {
		String saltedPassword = salt + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;
	}


//	public Boolean login(String username, String salt, String password, String suppliedHashedPassword) {
//
//		Boolean isAuthenticated = false;
//
//		// remember to use the same SALT value use used while storing password
//		// for the first time.
//		String saltedPassword = salt + password;
//		String hashedPassword = generateHash(saltedPassword);
//
//		if (hashedPassword.equals(suppliedHashedPassword)) {
//			isAuthenticated = true;
//		} else {
//			isAuthenticated = false;
//		}
//		return isAuthenticated;
//	}

	private String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

}
