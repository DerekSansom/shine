package com.sp.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class StreetPinAuth implements Authentication {

	private final String username;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean authenticated;
	private StreetPinUserDetails principal;

	public StreetPinAuth(StreetPinUserDetails principal, Collection<? extends GrantedAuthority> authorities) {
		this.principal = principal;
		this.username = principal.getUsername();
		this.authorities = authorities;
		authenticated = true;
	}

	@Override
	public String getName() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated = authenticated;
	}

}
