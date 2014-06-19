package com.sp.portal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sp.security.Role;
import com.sp.security.SpAuthority;
import com.sp.security.StreetPinAuth;
import com.sp.security.StreetPinUserDetails;

public class PortalControllerHelperMockitoTest {

	private PortalControllerHelper portalControllerHelper = new PortalControllerHelper();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void isAdminReturnsTrueOnRoleAdminWithoutMockito() {

		SpAuthority spauth = new SpAuthority(Role.ADMIN);
		StreetPinUserDetails principal = new StreetPinUserDetails("testUser", Arrays.asList(spauth), "password", 1);
		StreetPinAuth spauAuth = new StreetPinAuth(principal, Arrays.asList(spauth));
		spauAuth.setAuthenticated(true);

		boolean isAdmin = portalControllerHelper.isAdmin(spauAuth);
		assertTrue(isAdmin);
	}

	@Test
	public void isAdminReturnsFalseOnRoleUserWithoutMockito() {

		SpAuthority spauth = new SpAuthority(Role.USER);
		StreetPinUserDetails principal = new StreetPinUserDetails("testUser", Arrays.asList(spauth), "password", 1);
		StreetPinAuth spauAuth = new StreetPinAuth(principal, Arrays.asList(spauth));
		spauAuth.setAuthenticated(true);

		boolean isAdmin = portalControllerHelper.isAdmin(spauAuth);
		assertFalse(isAdmin);
	}

	@Test
	public void isAdminReturnsTrueOnRoleAdminWithMockito() {

		StreetPinUserDetails principal = Mockito.mock(StreetPinUserDetails.class);
		StreetPinAuth spauAuth = Mockito.mock(StreetPinAuth.class);
		Mockito.when(spauAuth.getPrincipal()).thenReturn(principal);
		Mockito.when(principal.hasRole(Role.ADMIN)).thenReturn(true);

		boolean isAdmin = portalControllerHelper.isAdmin(spauAuth);
		assertTrue(isAdmin);
	}


}



