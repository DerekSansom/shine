package com.sp.mobile.profile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

@Component
public class RegistrationValidator {

	private static final int NAME_MAX_LENGTH = 15;
	private static final int PASSWORD_MAX_LENGTH = 20;

	private static final int NAME_MIN_LENGTH = 4;
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	void validate(PlayerRegistrationForm player, BindingResult errors) {

		if (!StringUtils.hasLength(player.getPassword())) {
			errors.rejectValue("password", null, "password is required");

		} else if (player.getPassword().length() > PASSWORD_MAX_LENGTH
				|| player.getPassword().length() < NAME_MIN_LENGTH) {

			errors.rejectValue("password", null,
					String.format("password must be between %s and %s characters", NAME_MIN_LENGTH, PASSWORD_MAX_LENGTH));

		}
		if (!StringUtils.hasLength(player.getPasswordconf())) {
			errors.rejectValue("passwordconf", null, "password confirmation is required");

		}
		// only match passwords if no errors so far.
		if (!errors.hasErrors() && !player.getPassword().equals(player.getPasswordconf()))
		{
			errors.rejectValue("password", null, "passwords do not match");

		}
		if (player.getUsername() == null || player.getUsername().length() < NAME_MIN_LENGTH
				|| player.getUsername().length() > NAME_MAX_LENGTH) {
			errors.rejectValue("username", null,
					String.format("username must be between %s and %s characters", NAME_MIN_LENGTH, NAME_MAX_LENGTH));
		}
		if (player.getFullname() == null || player.getFullname().length() < NAME_MIN_LENGTH
				|| player.getFullname().length() > NAME_MAX_LENGTH) {
			errors.rejectValue("fullname", null,
					String.format("full name must be between %s and %s characters", NAME_MIN_LENGTH, NAME_MAX_LENGTH));
		}

		validateEmail(player.getEmail(), errors);

	}

	public void validateEmail(String email, BindingResult errors) {

		if (!StringUtils.hasLength(email)) {
			errors.rejectValue("email", null, "email is required");
		} else {
			Matcher matcher = EMAIL_PATTERN.matcher(email);
			if (!matcher.matches()) {
				errors.rejectValue("email", null, "not a valid email address");
			}

		}

	}

}
