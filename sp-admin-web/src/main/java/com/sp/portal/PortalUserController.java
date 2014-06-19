package com.sp.portal;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.security.Role;

import shine.app.UserManager;
import shine.dao.exception.ShineException;

@Controller
@RequestMapping(value = "/portal")
public class PortalUserController extends PortalBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortalUserController.class);

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@Secured({ Role.ADMIN, Role.USER })
	public String getProfile(Principal principal) throws ShineException {
		int userId = controllerHelper.getUserId(principal);
	    logger.info("PortalController - Publish %d",userId);

		return "portal/portalprofile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@Secured({ Role.ADMIN, Role.USER })
	public String postProfile(Principal principal) throws ShineException {
		int userId = controllerHelper.getUserId(principal);
	    logger.info("PortalController - PostPublish %d",userId);

		return "portal/portalprofile";
	}

	@RequestMapping(value = "/credits/add")
	@Secured({ Role.ADMIN, Role.USER })
	public String addCredits() throws ShineException {
		return "portal/portalcreditsadd";
	}

	@RequestMapping(value = "/activity")
	@Secured({ Role.ADMIN, Role.USER })
	public String activity() throws ShineException {
		return "portal/portalactivity";
	}

}
