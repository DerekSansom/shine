package com.sp.security;

import org.springframework.security.core.GrantedAuthority;

public class SpAuthority implements GrantedAuthority {

	private String authority;

	public SpAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}
