package com.sp.auth;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.sp.security.Role;
import com.sp.security.SpAuthority;
import com.sp.security.StreetPinAuth;
import com.sp.security.StreetPinUserDetails;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	@Autowired
	private AuthService authService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String token = ((HttpServletRequest) request).getHeader("token");

		if (token != null) {
			Long id = authService.getUserIdFromToken(token);

			if (id != null && id > 0) {
				String role = Role.PLAYER;
				SpAuthority spauth = new SpAuthority(role);

				StreetPinUserDetails userDetails = new StreetPinUserDetails("AuthPlayer", Arrays.asList(spauth), "", id.intValue());
				StreetPinAuth spauAuth = new StreetPinAuth(userDetails, Arrays.asList(spauth));
				spauAuth.setAuthenticated(true);
				SecurityContextHolder.getContext().setAuthentication(spauAuth);
			}
		}
		chain.doFilter(request, response);
	}
}
