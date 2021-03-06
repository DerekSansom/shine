package com.sp.mobile;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.objects.ShineObject;

@Controller
public class PlayerController {

	protected static Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private PlayerManager playerManager;

	@RequestMapping(value = "/userprofile/{id}")
	public String userProfile(@RequestParam("id") int id, Principal principal, Model model) {

		try {

			List<ShineObject> recentActivity = playerManager.getPlayerWithActivity(id, 0);
			if (CollectionUtils.isEmpty(recentActivity)) {
				return "playernotfound";
			}

			model.addAttribute("player", recentActivity.get(0));
			if (recentActivity.size() > 1) {
				model.addAttribute("activity", recentActivity.subList(1, recentActivity.size()));
			}
			return "userprofile";

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve player and activity", e);
			return "playernotfound";
		}
	}

}
