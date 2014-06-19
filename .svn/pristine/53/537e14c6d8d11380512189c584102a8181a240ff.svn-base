package com.sp.mobile;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.mobile.board.BoardController;
import com.sp.security.Role;

@Controller
public class HomeController {

	@Autowired
	private BoardController boardController;

	@RequestMapping(value = "/")
	@Secured(Role.USER)
	public String home() throws ShineException {
		System.out.println("secured: redirecting /main");
		return "redirect:/mw/map#controls";
//
//		return new RedirectView("/main", true, true, true);
	}

	@RequestMapping(value = "")
	@Secured(Role.USER)
	public String defaultHome() throws ShineException {
		System.out.println("secured: redirecting default home");
		return "redirect:/mw/map#controls";
//
//		return new RedirectView("/main", true, true, true);
	}

	@RequestMapping(value = "/main")
	public String straightToHome() throws ShineException {
		System.out.println("HomController: main.jsp");
		return "forward:/mw/map#controls";
	}

	@Secured(Role.USER)
	@RequestMapping(value = "/main2")
	public String toMain2() throws ShineException {
		System.out.println("HomController: main2.jsp");
		return "main2";
	}

	@RequestMapping(value = "/redir")
	public String redirect() throws ShineException {
		System.out.println("HomController: tomain.jsp");
		return "tomain";
	}

	@RequestMapping(value = "/menu")
	public String showMenu() throws ShineException {
		return "menu";
	}

	@RequestMapping(value = "/{shortname}")
	public String getByHappyUrl(@PathVariable("shortname") String shortname, Principal principal, Model model) throws ShineException {
		return boardController.getBoardByName(shortname, principal, model);
	}

}
