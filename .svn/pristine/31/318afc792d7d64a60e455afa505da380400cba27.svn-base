package com.sp.portal;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.app.UserManager;
import shine.dao.exception.ShineException;

import com.shine.AvailableCredits;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
@RequestMapping(value = "/portal")
public class PortalController extends PortalBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortalController.class);

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "")
	@Secured({ Role.ADMIN, Role.USER })
	public String home() throws ShineException {
	    logger.info("PortalController - boards");

		return "portal/portalpublish";
	}

	@RequestMapping(value = "/unsecured")
	public String unsecure() throws ShineException {
		String string = "portal/splash";
		return string;
	}

	@RequestMapping(value = "/publish")
	@Secured({ Role.ADMIN, Role.USER })
	public String publish(Model model, Principal principal) throws ShineException {
		Logger logger = LoggerFactory.getLogger(PortalController.class);
	    logger.info("PortalController - Publish");

	    // TODO: how best to get this into each JSP so it can be accessed from leftsection?
	    int userId = controllerHelper.getUserId(principal);
		AvailableCredits availableCredits = userManager.getAvailableCreditsForUserId(userId);
		model.addAttribute("availableCredits",availableCredits);
;	    return "portal/portalpublish";
	}

}
