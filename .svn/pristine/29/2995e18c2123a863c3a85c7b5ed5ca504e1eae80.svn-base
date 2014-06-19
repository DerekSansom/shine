package com.sp.mobile.board;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shine.app.BoardCreator;
import shine.app.BoardManager;
import shine.dao.exception.ShineException;

import com.shine.boards.BoardCategory;
import com.shine.boards.NoticeBoard;
import com.shine.objects.ShineLocation;
import com.sp.security.StreetPinUserDetails;

@Controller
public class BoardController {

	protected static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardCreator boardCreator;

	@Autowired
	private BoardManager boardManager;

	@RequestMapping(value = "/boardlist")
	public String getBoard(Principal principal, Model model) {

		return "boardlist";

	}

	@RequestMapping(value = "/board/{name}")
	public String getBoardByName(@PathVariable("name") String name, Principal principal, Model model) {

		try {

			NoticeBoard board = retrieveBoard(name, principal, model);

			reduceBoardAdsAndNoticesCount(board);
			logger.debug("returning " + board);
			return "board";

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve board", e);
			return "boardnotfound";
		}
	}

	@RequestMapping(value = "/board")
	public String getBoard(@RequestParam("id") int id, Principal principal, Model model) {

		try {

			NoticeBoard board = retrieveBoard(id, principal, model);

			reduceBoardAdsAndNoticesCount(board);

			logger.debug("returning " + board);
			return "board";

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve board", e);
			return "boardnotfound";
		}
	}

	@RequestMapping(value = "/boardads")
	public String getBoardWithAds(@RequestParam("id") int id, Principal principal, Model model) {

		try {

			NoticeBoard board = retrieveBoard(id, principal, model);
			board.setNotices(null);
			logger.debug("returning " + board);
			return "board";

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve board", e);
			return "boardnotfound";
		}
	}

	private void reduceBoardAdsAndNoticesCount(NoticeBoard board) {

		if (board.getAds().size() > 3) {
			board.setAds(board.getAds().subList(0, 3));
		}
		if (board.getNotices().size() > 3) {
			board.setNotices(board.getNotices().subList(0, 3));
		}

	}

	private NoticeBoard retrieveBoard(String name, Principal principal, Model model) throws ShineException {

		NoticeBoard board = null;
		int userId = 0;
		if (principal != null) {
			StreetPinUserDetails activeUser = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
			userId = activeUser.getId();

		}
		board = boardManager.getBoardByName(name, userId);


		model.addAttribute("board", board);
		model.addAttribute("boardCssClass", getBoardCategoryCssClass(board.getCategory()));

		return board;
	}

	private NoticeBoard retrieveBoard(int id, Principal principal, Model model) throws ShineException {

		NoticeBoard board = null;
		if (principal == null) {
			board = boardManager.getAnonNoticeBoard(id);
		} else {

			StreetPinUserDetails activeUser = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
			int playerId = activeUser.getId();
			board = boardManager.getNoticeBoard(id, playerId);
		}

		model.addAttribute("board", board);
		model.addAttribute("boardCssClass", getBoardCategoryCssClass(board.getCategory()));

		return board;
	}

	private String getBoardCategoryCssClass(BoardCategory boardCategory) {
		if (boardCategory == null) {
			return "cat-default";
		}

		switch (boardCategory) {
		// Change classname to change category image :-cat-default
		// cat-barclub < cat-books < cat-default < cat-events < cat-outdoors <
		// cat-poi < cat-restaurants < cat-retails < cat-social < cat-sports <
		// cat-stations < cat-tickets

		case EDU:
			return "cat-books";
		case ENT:
			return "cat-tickets";
		case BARCLUB:
			return "cat-barclub";
		case EVENT:
			return "cat-events";
		case FOOD_DRK:
			return "cat-restaurants";
		case GTOUTDRS:
			return "cat-outdoors";
		case POI:
			return "cat-poi";
		case RETAIL:
			return "cat-retails";
		case SOCIAL:
			return "cat-social";
		case SPORT:
			return "cat-sports";
		case TRANS:
			return "cat-stations";
		case DFLT:
		default:
			return "cat-default";
		}

	}

	private int getPlayerId(Principal principal) {
		StreetPinUserDetails activeUser = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (activeUser != null) {
			int playerId = activeUser.getId();

			return playerId;
		}
		throw new IllegalArgumentException("No playerId found");
	}

	@RequestMapping(value = "/boardposts")
	public String getBoardWithPosts(@RequestParam("id") int id, Principal principal, Model model) {

		try {

			NoticeBoard board = retrieveBoard(id, principal, model);
			if (board.getAds().size() > 3) {
				board.setAds(board.getAds().subList(0, 3));
			}
			logger.debug("returning " + board);
			return "board";

		} catch (ShineException e) {
			logger.warn("mw, failed to retrieve board", e);
			return "boardnotfound";
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/createboard", method = RequestMethod.GET)
	public String createBoard(Principal principal, Model model) {

		return "createboard";

	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/createboard", method = RequestMethod.POST)
	public String createBoard(Principal principal, Model model, NewBoardForm newBoardForm, BindingResult errors)
			throws ShineException {
		ShineLocation loc = new ShineLocation(newBoardForm.getBoardlat(), newBoardForm.getBoardlng());
		int playerId = getPlayerId(principal);
		int id = boardCreator.createBoard(newBoardForm.getCreatename(), newBoardForm.getLandadrs(), playerId, loc,
				BoardCategory.valueOf(newBoardForm.getCategory1()));

		if (id > 0) {
			return getBoard(id, principal, model);
		}
		return "createboard";

	}

	@ModelAttribute("createboard")
	public NewBoardForm getPlayerRegistrationObject() {
		return new NewBoardForm();
	}

	@RequestMapping(value = "/board2")
	public String getBoardstatic() {

		String ret = "board2";
		return ret;

	}
}
