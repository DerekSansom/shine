package com.sp.mobile.maps;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import shine.app.LocationObjectsHandler;
import shine.dao.exception.ShineException;
import shine.xml.XmlUtil;

import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.sp.mobile.AbstractController;
import com.sp.mobile.board.NewBoardForm;

@Controller
public class MapsController extends AbstractController {

	private static final int DEFAULT_RADIUS = 50;
	static final String XML_DECL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	@Autowired
	private LocationObjectsHandler locationObjectsHandler;

	// @Autowired
	// private TrackHandler handler;
	//
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public String listBoards(double lat, double lang, Principal principal, Model model) {

		int userId = getUserIdIfPresent(principal);
		ShineLocation loc = new ShineLocation(lat, lang);

		try {
			List<ShineObject> objects = locationObjectsHandler.initialReg(userId, null, loc, DEFAULT_RADIUS);
			model.addAttribute("boards", objects);
			return "boardlist";
		} catch (ShineException e) {
			return "errors/error";
		}

	}

	@RequestMapping(value = "/notify", method = RequestMethod.GET)
	protected @ResponseBody
	String doResponse(double lat, double lng, Principal principal, Model model) throws ShineException {

		int userId = getUserIdIfPresent(principal);
		ShineLocation loc = new ShineLocation(lat, lng);

		try {
			StringBuilder xml = new StringBuilder(XML_DECL).append("<xml>").append("<objects>");

			List<ShineObject> objects = locationObjectsHandler.initialReg(userId, null, loc, DEFAULT_RADIUS);
			for (ShineObject shineObject : objects) {
				xml.append(XmlUtil.getXml(shineObject));
			}

			xml.append("</objects>").append("</xml>");

			return xml.toString();

		} catch (ShineException e) {
			return "errors/error";
		}

	}

	@RequestMapping(value = "/maps", method = RequestMethod.GET)
	public String register() {

		return "maps";

	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map() {
		return "map";
	}

	@ModelAttribute("createboard")
	public NewBoardForm getPlayerRegistrationObject() {
		return new NewBoardForm();
	}

}
