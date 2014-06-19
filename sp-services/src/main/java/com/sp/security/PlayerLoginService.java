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

import shine.app.PasswordManager;
import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.objects.Player;

@Component("playerLoginService")
public class PlayerLoginService implements UserDetailsService, AuthenticationManager {

	@Autowired
	private PlayerManager playerManager;

	@Autowired
	private PasswordManager passwordManager;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String name = auth.getName();
		Object cred = auth.getCredentials();
		String password = null;
		if (cred != null) {
			password = cred.toString();
		}
		try {
			Player user = passwordManager.doLogin(name, password, "mw", null, null);
			if (user == null) {
				throw new BadCredentialsException("User does not exists!");
			}
			String role = Role.USER;
			if ("admin".equals(name)) {
				role = Role.ADMIN;
			}
			SpAuthority spauth = new SpAuthority(role);
			StreetPinUserDetails principal = new StreetPinUserDetails(user.getUsername(), Arrays.asList(spauth),
					password, user.getId());
			StreetPinAuth spauAuth = new StreetPinAuth(principal, Arrays.asList(spauth));
			spauAuth.setAuthenticated(true);
			return spauAuth;
		} catch (ShineException e) {
			throw new BadCredentialsException("User does not exists!");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

		Player p = playerManager.getUserByUsername(arg0);
		SpAuthority auth = null;
		String password = null;
		if (p != null) {
			new SpAuthority("ROLE_USER");
			password = p.getPassword();
		} else {
			new SpAuthority("NONE");
		}

		StreetPinUserDetails details = new StreetPinUserDetails(arg0, Arrays.asList(auth), password, p.getId());

		return details;
	}
}
