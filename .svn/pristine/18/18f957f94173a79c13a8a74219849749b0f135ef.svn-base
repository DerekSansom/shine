package com.sp.portal;

import static org.junit.Assert.assertEquals;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import shine.app.BrandManager;
import shine.app.UserManager;
import shine.dao.exception.ShineException;

import com.shine.boards.CorpBrand;
import com.sp.portal.brands.BrandsResult;

@RunWith(MockitoJUnitRunner.class)
public class PortalBrandControllerTest {

	@Mock
	private BrandManager brandManager;
	
	@Mock
	private UserManager userManager;

	@Mock
	private PortalControllerHelper portalControllerHelper;

	@InjectMocks
	private PortalBrandController brandController;
	
	@Mock
	Model model;

	@Mock
	Principal principal;
	
	List<CorpBrand> brandsForAdmin;
	List<CorpBrand> brandsForUser;

	@Before
	public void setUp() {
		
		CorpBrand user1b1 = new CorpBrand(1,1,"User1 Brand 1");
		CorpBrand user1b2 = new CorpBrand(1,1,"User1 Brand 2");
		
		brandsForUser = new ArrayList<CorpBrand>(1);
		brandsForUser.add(user1b1);
		brandsForUser.add(user1b2);

		brandsForAdmin = new ArrayList<CorpBrand>(1);
		brandsForAdmin.add(user1b1);
		brandsForAdmin.add(user1b2);
		brandsForAdmin.add(new CorpBrand(3,3,"User2 Brand 1"));
		brandsForAdmin.add(new CorpBrand(4,4,"User3 Brand 1"));
	}
	
	@Test
	public void testBrandsForAdmin() throws ShineException {
		//given
		when(portalControllerHelper.isAdmin(principal)).thenReturn(true);
		when(brandManager.getAllBrandsPaginated(anyInt(),anyInt())).thenReturn(brandsForAdmin);
		
		// when
		String jspPage = brandController.brands(model, principal, 0);

		// then
		assertEquals("portal/portalbrands", jspPage);
		verify(brandManager).getAllBrandsPaginated(anyInt(), anyInt());
		verify(model).addAttribute(anyString(), anyObject());
	}

	@Test
	public void testBrandsFoUser() throws ShineException {
		//given
		when(portalControllerHelper.isAdmin(principal)).thenReturn(false);
		when(portalControllerHelper.getUserId(principal)).thenReturn(0);
		when(brandManager.getBrandsForUserId(0)).thenReturn(brandsForUser);
		
		// when
		String jspPage = brandController.brands(model, principal, 0);

		// then
		assertEquals("portal/portalbrands", jspPage);
		verify(portalControllerHelper).getUserId(principal);
		verify(brandManager).getBrandsForUserId(any(Integer.class));
		verify(model).addAttribute(anyString(), anyObject());
	}
}
