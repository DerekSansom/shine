package com.sp.mobile;

import java.security.Principal;

import org.springframework.security.core.Authentication;

import com.sp.security.StreetPinUserDetails;

public abstract class AbstractController {

	protected int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

	protected int getUserIdIfPresent(Principal principal) {

		if (principal != null) {
			StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
			if (user != null) {
				return user.getId();
			}
		}
		return 0;
	}
}
