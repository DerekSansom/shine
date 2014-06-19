package com.sp.security;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.sp.entity.UserEntity;
import com.sp.user.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class PortalLoginServiceTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private PortalLoginService userLoginService;

	@Mock
	private Authentication auth;

	private UserEntity user = new UserEntity();

	@Before
	public void setup() {
		user = new UserEntity();
		user.setUsername("aUsername");
		user.setRole("user");

		when(auth.getName()).thenReturn("dezzer");
		when(auth.getCredentials()).thenReturn("munieys3");
		when(userDao.getUser("dezzer", "2ffe547609697313d5144e59adbc7ed7102b4dd6")).thenReturn(user);

	}

	@Test
	public void testAuthenticate() {

		// when
		Authentication result = userLoginService.authenticate(auth);

		// then
		assertEquals("aUsername", result.getName());
		assertEquals(Role.USER, result.getAuthorities().iterator().next().getAuthority());
	}

	@Test
	public void testAuthenticateAdmin() {
		// given
		user.setRole("Administrator");

		// when
		Authentication result = userLoginService.authenticate(auth);

		// then
		assertEquals("aUsername", result.getName());
		assertEquals(Role.ADMIN, result.getAuthorities().iterator().next().getAuthority());
	}

	@Test(expected = BadCredentialsException.class)
	public void throwsBadCredentialsWhenNoUser() {
		// given
		when(auth.getName()).thenReturn("nonExistentName");

		// when
		userLoginService.authenticate(auth);
	}

	@Test
	public void testLoadUserByUsername() {
		// given
		when(userDao.getUserByName("dezzer")).thenReturn(user);

		// when
		UserDetails result = userLoginService.loadUserByUsername("dezzer");

		// then
		assertEquals("dezzer", result.getUsername());

	}

}
