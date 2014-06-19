package com.sp.mobile;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

@Controller
public class SecuredPageController {

	@RequestMapping(value = "/secure")
	@Secured("ROLE_USER")
	public String register() throws ShineException {
		System.out.println("SecuredPageController: secured...");

		return "secured";
	}

}
