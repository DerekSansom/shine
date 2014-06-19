package com.sp.mobile.profile;

import org.springframework.stereotype.Component;

import com.shine.boards.AdCategory;
import com.shine.objects.Player;

@Component
public class ProfileFormBeanMapper {

	public static final String YES = "on";

	private static final String NO = "off";

	Player createUser(PlayerRegistrationForm player) {

		Player user = new Player();
		user.setForename(player.getFullname());
		user.setEmail(player.getEmail());
		user.setPassword(player.getPassword());
		user.setPhone(player.getPhone());
		user.setUsername(player.getUsername());
		return user;

	}

	ProfileUpdateForm createProfileUpdate(Player user) {

		ProfileUpdateForm profileUpdate = new ProfileUpdateForm();
		profileUpdate.setBiog(user.getBiog());
		if (user.getDob() != null) {
			profileUpdate.setDefslide(user.getDob().toString());
		} else {
			profileUpdate.setDefslide("");
		}
		profileUpdate.setEmail(user.getEmail());
		profileUpdate.setForename(user.getForename());
		profileUpdate.setPassword(user.getPassword());
		profileUpdate.setPhone(user.getPhone());
		profileUpdate.setStatus(user.getStatus());
		profileUpdate.setSurname(user.getSurname());
		profileUpdate.setUsername(user.getUsername());
		setGender(profileUpdate, user);
		setOffers(profileUpdate, user);

		if (user.isHasIcon()) {
			profileUpdate.setImg("images/user/" + user.getId() + ".png");
		} else {
			profileUpdate.setImg("static/images/image-profile.png");
		}

		return profileUpdate;
	}

	private void setOffers(ProfileUpdateForm profileUpdate, Player user) {

		String offers = user.getOffers();
		profileUpdate.setEvents(mapCategory(offers, AdCategory.EVENTS));
		profileUpdate.setFoodrink(mapCategory(offers, AdCategory.FOOD_DRINK));
		profileUpdate.setTravel(mapCategory(offers, AdCategory.TRAVEL));
		profileUpdate.setHgarden(mapCategory(offers, AdCategory.HOME_GARDEN));
		profileUpdate.setJobs(mapCategory(offers, AdCategory.JOBS));
		profileUpdate.setLeisure(mapCategory(offers, AdCategory.LEISURE));
		profileUpdate.setServices(mapCategory(offers, AdCategory.SERVICES));
		profileUpdate.setShopping(mapCategory(offers, AdCategory.SHOPPING));

	}

	private String mapCategory(String offers, AdCategory adCategory) {
		if (offers != null && offers.indexOf(adCategory.getCode()) > -1) {
			return YES;
		}
		return NO;
	}

	private void setGender(ProfileUpdateForm profileUpdate, Player user) {

		if (user.getGender() == null) {
			profileUpdate.setGender("");
			return;
		}

		switch (user.getGender()) {
		case "m":
		case "M":
			profileUpdate.setGender("M");
			break;
		case "f":
		case "F":
			profileUpdate.setGender("F");
			break;
		default:
			profileUpdate.setGender("");
		}

	}

}
