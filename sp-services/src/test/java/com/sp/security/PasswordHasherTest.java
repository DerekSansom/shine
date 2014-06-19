package com.sp.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PasswordHasherTest {


	@Test
	public void hashPassword() {

		PasswordHasher passwordHasher = new PasswordHasher();
		String hashed = passwordHasher.hash("user", "password");
		// then
		System.out.println(hashed);
		assertEquals("c73ba2982c55b7ead0e4098a92f722bdb3a3b3d8", hashed);
	}


}
