package com.sp.mobile.profile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import shine.app.PlayerManager;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.AdCategory;
import com.shine.objects.Player;
import com.sp.img.ImageScaler;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
public class PlayerProfileController {
	static final String PROFILE_PAGE = "profile";
	private static final String PROFILE_PAGE_LITE = "proflite";

	private static DateTimeFormatter dobFormat = DateTimeFormat.shortDate();

	protected static Logger logger = LoggerFactory.getLogger(PlayerProfileController.class);

	@Autowired
	private PlayerManager playerManager;

	@Autowired
	private ProfileFormBeanMapper profileFormBeanMapper;

	@Autowired
	private ImageScaler imageScaler;

	@Secured(Role.USER)
	@RequestMapping(value = "/myprofile")
	public String myProfile(Principal principal, Model model) {

		int id = getUserId(principal);
		try {

			Player user = playerManager.getUser(id);
			if (user == null) {
				return "playernotfound";
			}
			ProfileUpdateForm formBean = profileFormBeanMapper.createProfileUpdate(user);
			model.addAttribute("profileUpdate", formBean);
			model.addAttribute("user", user);
			return PROFILE_PAGE;

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve player and activity", e);
			return "playernotfound";
		}
	}

	@Secured(Role.USER)
	@RequestMapping(value = "/proflite")
	public String profileLite(Principal principal, Model model) {

		int id = getUserId(principal);
		try {

			Player user = playerManager.getUser(id);
			if (user == null) {
				return "playernotfound";
			}
			ProfileUpdateForm formBean = profileFormBeanMapper.createProfileUpdate(user);
			model.addAttribute("profileUpdate", formBean);
			model.addAttribute("user", user);
			return PROFILE_PAGE_LITE;

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve player and activity", e);
			return "playernotfound";
		}
	}

	@Secured(Role.USER)
	@RequestMapping(value = "/profile/update")
	public String profileUpdate(@ModelAttribute("profileUpdate") ProfileUpdateForm profileUpdate, Principal principal,
			Model model) throws ShineException {

		int id = getUserId(principal);
		Player user = populateUser(profileUpdate, id);
		try {
			MultipartFile imageUpload = profileUpdate.getFile();
			if (imageUpload != null && !ArrayUtils.isEmpty(imageUpload.getBytes())) {
				user = playerManager.updateUserWithImage(user, getImage(profileUpdate), profileUpdate.getFile().getSize());

			} else {
				user = playerManager.updateUser(user);
			}
			ProfileUpdateForm formBean = profileFormBeanMapper.createProfileUpdate(user);

			model.addAttribute("profileUpdate", formBean);
			model.addAttribute("msg", "Your profile has been updated");

			return PROFILE_PAGE;
		} catch (ShineException e) {
			if (e.getCode() == GeneralError.ERROR_MAX_IMG_SIZE.getCode()) {

				model.addAttribute("msg",
						"That image is too big, auto processing coming soon");
				return PROFILE_PAGE;
			}

			logger.error("Failed to update profile", e);
			model.addAttribute("exception", e);

			return "errors/error";
		}

		catch (IOException e) {
			model.addAttribute("msg", "error uploading file, please try again");
			return PROFILE_PAGE;
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/proflite/update")
	public String profliteUpdate(@ModelAttribute("profileUpdate") ProfileUpdateForm profileUpdate, Principal principal,
			Model model) {

		int id = getUserId(principal);
		Player user = populateUser(profileUpdate, id);
		try {
			if (profileUpdate.getFile() != null) {
				user = playerManager.updateUserWithImage(user, getImage(profileUpdate),
						profileUpdate.getFile().getSize());

			} else {
				user = playerManager.updateUser(user);
			}
			ProfileUpdateForm formBean = profileFormBeanMapper.createProfileUpdate(user);

			model.addAttribute("profileUpdate", formBean);
			model.addAttribute("msg", "Your profile has been updated");

			return PROFILE_PAGE_LITE;
		} catch (ShineException e) {
			model.addAttribute("msg", "There was an error: " + e.getMessage());

			return PROFILE_PAGE_LITE;
		} catch (IOException e) {
			model.addAttribute("msg", "error uploading file, please try again");
			return PROFILE_PAGE_LITE;
		}

	}

	private BufferedImage getImage(ProfileUpdateForm profileUpdate) throws IOException {

		return imageScaler.scaleImage(profileUpdate.getFile().getInputStream(), ShineProperties.maxImageDimension());
	}

	private Player populateUser(ProfileUpdateForm profileUpdate, int id) {
		Player user = new Player();
		user.setId(id);
		user.setBiog(profileUpdate.getBiog());
		user.setDob(toDateOfBirth(profileUpdate.getDefslide()));
		user.setEmail(profileUpdate.getEmail());
		user.setForename(profileUpdate.getForename());
		user.setGender(profileUpdate.getGender());
		user.setOffers(toOffers(profileUpdate));
		user.setPhone(profileUpdate.getPhone());
		user.setStatus(profileUpdate.getStatus());
		user.setSurname(profileUpdate.getSurname());

		return user;
	}

	private String toOffers(ProfileUpdateForm profileUpdate) {

		StringBuffer offers = new StringBuffer();
		addAdCategory(profileUpdate.getEvents(), AdCategory.EVENTS, offers);
		addAdCategory(profileUpdate.getFoodrink(), AdCategory.FOOD_DRINK, offers);
		addAdCategory(profileUpdate.getHgarden(), AdCategory.HOME_GARDEN, offers);
		addAdCategory(profileUpdate.getJobs(), AdCategory.JOBS, offers);
		addAdCategory(profileUpdate.getLeisure(), AdCategory.LEISURE, offers);
		addAdCategory(profileUpdate.getServices(), AdCategory.SERVICES, offers);
		addAdCategory(profileUpdate.getShopping(), AdCategory.SHOPPING, offers);
		addAdCategory(profileUpdate.getTravel(), AdCategory.TRAVEL, offers);
		return offers.toString();
	}

	private void addAdCategory(String offer, AdCategory adCategory, StringBuffer offers) {
		if (ProfileFormBeanMapper.YES.equals(offer)) {
			offers.append(adCategory.getCode());
		}

	}

	private Date toDateOfBirth(String defslide) {
		if (!StringUtils.hasText(defslide)) {
			return null;
		}

		return dobFormat.parseLocalDate(defslide).toDate();
	}


	private int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}
}
