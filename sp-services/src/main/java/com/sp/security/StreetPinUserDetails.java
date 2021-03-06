package com.sp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StreetPinUserDetails implements UserDetails {

	private final String username;
	private final String password;
	private Collection<? extends GrantedAuthority> authorities;
	private final int id;

	public StreetPinUserDetails(String username, Collection<? extends GrantedAuthority> authorities, String password, int id) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(String role) {
		if (authorities != null) {
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(role)) {
					return true;
				}
			}
		}

		return false;
	}

	public int getId() {
		return id;
	}

	@Override public String toString() {
	    StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
	    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
	    result.append("Username: " + id);
	    result.append("}");
	    return result.toString();
	  }
}
