package com.sp.admin;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.security.Role;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@RequestMapping(value = "")
	@Secured(Role.ADMIN)
	public String home() throws ShineException {
		return "admin/home";
	}

	@RequestMapping(value = "/landing")
	public String landing() throws ShineException {
		return "admin/landing";
	}

}
