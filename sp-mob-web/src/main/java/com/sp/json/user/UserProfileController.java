package com.sp.json.user;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.objects.OtherPlayer;
import com.sp.json.AbstractJsonController;

@Controller
public class UserProfileController extends AbstractJsonController {

	@Autowired
	private PlayerManager playerManager;

	@RequestMapping(value = "/json/user/{id}/profile", produces = "application/json")
	@ResponseBody
	public OtherPlayer getUserProfile(@PathVariable("id") Integer id, Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response)
			throws ShineException,
			JsonGenerationException, JsonMappingException, IOException {

		setAccessControlAllowOrigin(request, response);

		playerManager.getPlayer(id);
		OtherPlayer profile = playerManager.getPlayer(id);
		return profile;
	}

}
