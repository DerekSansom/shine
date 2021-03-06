package com.sp.portal;

import static org.junit.Assert.assertEquals;

import java.security.Principal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import shine.app.UserManager;
import shine.dao.exception.ShineException;

import com.shine.AvailableCredits;

@RunWith(MockitoJUnitRunner.class)
public class PortalControllerTest {

	@Mock
	private UserManager userManager;

	@Mock
	private PortalControllerHelper portalControllerHelper;

	@InjectMocks
	private PortalController controller;

	@Test
	public void publishAddsCreditsToModel() throws ShineException {
		// given
		Model model = Mockito.mock(Model.class);
		Principal principal = Mockito.mock(Principal.class);
		AvailableCredits expectedAvailableCredits = new AvailableCredits(33, 0, 0);
		Mockito.when(portalControllerHelper.getUserId(principal)).thenReturn(666);
		Mockito.when(userManager.getAvailableCreditsForUserId(666)).thenReturn(expectedAvailableCredits);

		// when
		String jspPage = controller.publish(model, principal);

		// then
		Mockito.verify(model).addAttribute("availableCredits", expectedAvailableCredits);
		assertEquals("portal/portalpublish", jspPage);
	}
}
