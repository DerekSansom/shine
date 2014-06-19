package com.sp.portal;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shine.app.AdManager;
import shine.app.BoardManager;
import shine.app.BrandManager;
import shine.dao.exception.ShineException;

import com.shine.boards.AdCategory;
import com.shine.boards.CorpBrand;
import com.shine.boards.NoticeBoard;
import com.sp.portal.boards.BoardEditResult;
import com.sp.portal.boards.BoardNewResult;
import com.sp.portal.boards.BoardResult;
import com.sp.security.Role;

@Controller
@RequestMapping(value = "/portal")
public class PortalBoardController extends PortalBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortalBoardController.class);

	@Autowired
	private BoardManager boardManager;

	@Autowired
	private BrandManager brandManager;
	
	@Autowired 
	private AdManager adManager;
	
	@RequestMapping(value = "/boards")
	@Secured({ Role.ADMIN, Role.USER })
	public String boards(Model model, Principal principal, @RequestParam(required = false, value = "start") Integer start)
			throws ShineException {

	    logger.info("PortalBoardController - boards");

	    int count = 20;
		start = controllerHelper.calculateStart(start);
		int totalBoards = 0;
		List<NoticeBoard> usersBoards = null;
		int userId = controllerHelper.getUserId(principal);
		// for now only paginate for admin, users just get all their boards back
		if (controllerHelper.isAdmin(principal)) {
			usersBoards = boardManager.getAllBoardsPaginated(start - 1, count);
			totalBoards = boardManager.getAllBoardsCount();
		} else {
			usersBoards = boardManager.getBoardsByCreatorId(userId);
			totalBoards = usersBoards.size();
			count = totalBoards;
		}

		BoardResult result = populateResult(usersBoards, start, count, totalBoards);
		model.addAttribute("result", result);
		return "portal/portalboards";
	}

	@RequestMapping(value = "/boards/new")
	@Secured({ Role.ADMIN, Role.USER })
	public String addBoard(Model model, Principal principal) throws ShineException {
		int userId = controllerHelper.getUserId(principal);
		List<CorpBrand> brands = brandManager.getBrandsForUserId(userId);
		List<AdCategory> adCategories = adManager.getAllAdvertCategories();
		BoardNewResult result = new BoardNewResult(brands,adCategories);
		model.addAttribute("result",result);
		return "portal/portalboardsnew";
	}

	@RequestMapping(value = "/boards/{id}")
	@Secured({ Role.ADMIN, Role.USER })
	public String getBoard(@PathVariable("id") int boardId,
							Model model, 
							Principal principal) throws ShineException {
		int userId = controllerHelper.getUserId(principal);
		NoticeBoard board = boardManager.getNoticeBoard(boardId, userId);
		List<CorpBrand> brands = brandManager.getBrandsForUserId(userId);

		BoardEditResult result = new BoardEditResult(brands,null,board);
		model.addAttribute("result",result);
		return "portal/portalboardedit";
	}

	private BoardResult populateResult(List<NoticeBoard> boards, Integer start, Integer count, Integer totalBoards) {

		if (boards.isEmpty()) {
			return new BoardResult(boards, boards, null, null, null);
		}

		Collections.sort(boards, new Comparator<NoticeBoard>() {
				@Override
				public int compare(NoticeBoard o1, NoticeBoard o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});

		List<NoticeBoard> activeBoards = new ArrayList<>();
		List<NoticeBoard> draftBoards = new ArrayList<>();

		for (NoticeBoard noticeBoard : boards) {
			if (Boolean.TRUE == noticeBoard.getActive()) {
				activeBoards.add(noticeBoard);
			} else {
				draftBoards.add(noticeBoard);
			}

		}

		return new BoardResult(activeBoards, draftBoards, start, count, totalBoards);
	}
}
