package com.sp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/oauth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "")
	public String home(Model model) {

		String token = authService.authenticate(1l);

		model.addAttribute("token", token);
		return "auth/auth";
	}
}
