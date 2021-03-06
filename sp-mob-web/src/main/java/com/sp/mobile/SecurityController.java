package com.sp.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/portal/auth")
public class SecurityController {

	protected static Logger logger = LoggerFactory.getLogger(SecurityController.class);

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "logout", required = false) boolean loggedout, ModelMap model) {

		logger.debug("Received request to show login page- error = " + error);

		if (error) {
			// Assign an error message
			model.put("error", "You have entered an invalid username or password!");
		} else if (loggedout) {
			// Assign an error message
			model.put("error", "You have logged out");
		} else {
			model.put("error", "");
		}
		return "portal/login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginFailed(ModelMap model) {
		return getLoginPage(true, false, model);

	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		return "portal/denied";
	}

	@RequestMapping(value = "/loggedout", method = RequestMethod.GET)
	public String loggedout(ModelMap model) {
		return getLoginPage(false, true, model);
	}
}