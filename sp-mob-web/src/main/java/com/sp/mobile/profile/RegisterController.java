package com.sp.mobile.profile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.objects.Player;
import com.sp.security.PlayerLoginService;

@Controller
public class RegisterController {

	@Autowired
	private PlayerManager playerManager;

	@Autowired
	private RegistrationValidator validator;

	@Autowired
	private ProfileFormBeanMapper profileFormBeanMapper;

	@Autowired
	protected PlayerLoginService loginService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("playerRegistration") PlayerRegistrationForm player, Model model, BindingResult errors,
			HttpServletRequest request)
			throws ShineException {

		validator.validate(player, errors);
		if (errors.hasErrors()) {
			errors.rejectValue("global", null, "please correct the following");
			return "registration";

		}

		Player newPlayer = profileFormBeanMapper.createUser(player);

		try {
			playerManager.create(newPlayer);

			ProfileUpdateForm formBean = profileFormBeanMapper.createProfileUpdate(newPlayer);

			authenticateUserAndSetSession(newPlayer, request);
			model.addAttribute("profileUpdate", formBean);
			model.addAttribute("msg", "You have successfully registered, update your profile here");

			return "redirect:myprofile";// PlayerProfileController.PROFILE_PAGE;

		} catch (ShineException e) {
			errors.reject(e.getMessage());
			return "registration";

		}
	}

	@ModelAttribute("playerRegistration")
	public PlayerRegistrationForm getPlayerRegistrationObject() {
		return new PlayerRegistrationForm();
	}

	@RequestMapping(value = "/registration")
	public String registerPage() {
		System.out.println("RegisterController: registration page...");

		return "registration";
	}

	private void authenticateUserAndSetSession(Player user,
			HttpServletRequest request)
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = loginService.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

}
