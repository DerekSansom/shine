package com.sp.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sp.entity.UserEntity;
import com.sp.user.UserDao;

@Component
public class PortalLoginService implements UserDetailsService, AuthenticationManager {

	@Autowired
	private UserDao userDao;

	private PasswordHasher passwordHasher = new PasswordHasher();

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String name = auth.getName();
		Object cred = auth.getCredentials();
		String password = null;
		if (cred != null) {
			password = passwordHasher.hash(name, cred.toString());
		}
		UserEntity user = userDao.getUser(name, password);
		if (user == null) {
			throw new BadCredentialsException("User does not exist!");
		}
		String role = user.getRole().equalsIgnoreCase("Administrator") ? Role.ADMIN : Role.USER;
		SpAuthority spauth = new SpAuthority(role);
		StreetPinUserDetails principal = new StreetPinUserDetails(user.getUsername(), Arrays.asList(spauth), password, user.getId());
		StreetPinAuth spauAuth = new StreetPinAuth(principal, Arrays.asList(spauth));
		spauAuth.setAuthenticated(true);
		return spauAuth;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userDao.getUserByName(username);
		SpAuthority auth = null;
		String password = null;
		if (user != null) {
			auth = new SpAuthority("ROLE_USER");
			password = user.getPassword();
		} else {
			auth = new SpAuthority("NONE");
		}

		StreetPinUserDetails details = new StreetPinUserDetails(username, Arrays.asList(auth), password, user.getId());

		return details;
	}
}
