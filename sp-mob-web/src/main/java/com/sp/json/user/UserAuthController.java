package com.sp.json.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import shine.app.LoginManager;
import shine.dao.exception.ShineException;

import com.shine.auth.AuthToken;
import com.shine.auth.LoginRequest;
import com.shine.objects.Player;
import com.sp.auth.AuthFailedException;

@Controller
@RequestMapping("auth")
public class UserAuthController {

	@Autowired
	private LoginManager loginManager;

	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	AuthToken login(@RequestBody LoginRequest req) {
		
		try {
			Player user = loginManager.doLogin(req.getCredential(), req.getPassword(), "json", null, null);
			if (user != null) {
				return new AuthToken(user.getToken(), user.getUsername());
			}
		} catch (ShineException e) {
		}
		throw new AuthFailedException("auth failed");
	}
}
