package com.sp.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shine.app.PasswordManager;
import shine.dao.exception.ShineException;

@Controller
@RequestMapping(value = "/passwordreset")
public class PasswordController {

	@Autowired
	private PasswordManager passwordManager;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(String token, Model model) throws ShineException {

		if (token == null) {
			return "errors/passwordreset";
		}
		if (!passwordManager.isTokenValid(token)) {
			return "errors/passwordreset";
		}

		model.addAttribute("passwordResetForm", new PasswordResetForm(token));

		return "passwordreset";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String processForm(PasswordResetForm form, Model model) {

		String error = validateForm(form);
		if (error != null) {

			model.addAttribute("error", error);
			return "passwordreset";
		}

		try {
			passwordManager.resetPassword(form.getToken(), form.getNewPassword());
		} catch (ShineException e) {
			return "errors/passwordreset";
		}

		return "passwordresetsuccess";
	}

	private String validateForm(PasswordResetForm form) {
		if (!StringUtils.hasLength(form.getNewPassword())
				|| !StringUtils.hasLength(form.getConfirmPassword())) {
			return "both fields must be completed";
		}
		if (!form.getNewPassword().equals(form.getConfirmPassword())) {
			return "fields do not match";
		}

		if (form.getNewPassword().length() < 4 || form.getNewPassword().length() > 20) {
			return "password must be between 4 and 20 characters, contain at least one uppercase letter, one lowercase letter, a number, a non number and letter character, at least one asterix, and a bracket, it cannot contain any letters from your name nor your grandmothers age.";
		}

		return null;

	}

	@ModelAttribute("passwordResetForm")
	public PasswordResetForm getPasswordResetForm() {
		return new PasswordResetForm();
	}
}
