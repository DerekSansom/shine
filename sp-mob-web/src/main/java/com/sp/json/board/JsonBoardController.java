package com.sp.json.board;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shine.app.BoardManager;
import shine.dao.exception.ShineException;

import com.shine.boards.NoticeBoard;
import com.shine.objects.ShineLocation;
import com.sp.json.AbstractJsonController;
import com.sp.security.StreetPinUserDetails;

@Controller
public class JsonBoardController extends AbstractJsonController {

	protected static Logger logger = LoggerFactory.getLogger(JsonBoardController.class);

	@Autowired
	private BoardManager boardManager;

	@RequestMapping(value = "/json/board/{name}", produces = "application/json")
	@ResponseBody
	public NoticeBoard getBoardByName(@PathVariable("name") String name, Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response)
			throws ShineException, JsonGenerationException, JsonMappingException, IOException {

		setAccessControlAllowOrigin(request, response);

		NoticeBoard board = retrieveBoard(name, principal, model);
		logger.debug("returning " + board);
		return board;
	}

	@RequestMapping(value = "/json/boards", produces = "application/json")
	@ResponseBody
	public List<NoticeBoard> getBoardByLocation(@RequestParam(value = "loc", required = false) String location, Principal principal,
			Model model, HttpServletRequest request, HttpServletResponse response)
			throws ShineException,
			JsonGenerationException, JsonMappingException, IOException {

		setAccessControlAllowOrigin(request, response);

		ShineLocation loc = getLocation(location);
		
		List<NoticeBoard> boardDtos = boardManager.getNearBoards(loc);

		return boardDtos;
	}

	private ShineLocation getLocation(String location) {
		if (location == null) {
			return null;
		}
		String[] latLng = location.split(":");
		ShineLocation loc = new ShineLocation(Double.parseDouble(latLng[0]), Double.parseDouble(latLng[1]));
		return loc;
	}

	@RequestMapping(value = "/json/board/id/{id}", produces = "application/json")
	@ResponseBody
	public NoticeBoard getBoardByid(@PathVariable("id") int id, Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response)
			throws ShineException, JsonGenerationException, JsonMappingException, IOException {
		setAccessControlAllowOrigin(request, response);
		NoticeBoard board = retrieveBoard(id, principal, model);
		logger.debug("returning " + board);
		return board;
	}

	private NoticeBoard retrieveBoard(int id, Principal principal, Model model) throws ShineException {

		int userId = retrieveUserId(principal);
		NoticeBoard board = boardManager.getNoticeBoard(id, userId);
		reduceBoardAdsAndNoticesCount(board);
		return board;
	}

	private int retrieveUserId(Principal principal) {
		int userId = 0;
		if (principal != null) {
			StreetPinUserDetails activeUser = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
			userId = activeUser.getId();

		}
		return userId;
	}

	private NoticeBoard retrieveBoard(String name, Principal principal, Model model) throws ShineException {

		int userId = retrieveUserId(principal);
		NoticeBoard board = boardManager.getBoardByName(name, userId);
		board.setActiveUsers(null);
		board.setAds(null);
		board.setNotices(null);
		// reduceBoardAdsAndNoticesCount(board);
		return board;

	}

	private void reduceBoardAdsAndNoticesCount(NoticeBoard board) {

		if (board.getAds().size() > 3) {
			board.setAds(board.getAds().subList(0, 3));
		}
		if (board.getNotices().size() > 3) {
			board.setNotices(board.getNotices().subList(0, 3));
		}

	}

}
