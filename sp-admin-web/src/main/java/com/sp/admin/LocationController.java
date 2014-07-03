package com.sp.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.locations.BoardLocationsMapper;
import com.sp.security.Role;

@Controller
@RequestMapping(value = "/admin/locations")
public class LocationController {

	private static Logger log = LoggerFactory.getLogger(BoardLocationsMapper.class);

	@Autowired
	private BoardLocationsMapper boardLocationsMapper;

	@Autowired
	private BoardDao boardDao;

	@RequestMapping(value = "")
	@Secured(Role.ADMIN)
	public String getBoardsLocations(Model model) throws ShineException {

		return "admin/boardlocations/boardLocations";
	}

	@RequestMapping(value = "/finder")
	@Secured(Role.ADMIN)
	public String runBoardsLocationsFinder(Model model) throws ShineException {

		boardLocationsMapper.populateBoardLocations();

		return "admin/boardlocations/runnerResult";
	}

	@RequestMapping(value = "{boardId}/run")
	@Secured(Role.ADMIN)
	public String runBoardsLocationsFinder(@PathVariable("boardId") int boardId, Model model) throws ShineException {

		WebLogger webLog = new WebLogger();
		boardLocationsMapper.updateBoardLocation(boardId, webLog);
		model.addAttribute("logs", webLog.getLogs());
		return "admin/boardlocations/singleRunnerResult";
	}

	@RequestMapping(value = "/missing")
	@Secured(Role.ADMIN)
	public String showMissingBoards(Model model) throws ShineException {

		List<NoticeBoardEntity> boards = boardDao.getBoardsWithNoLocation();
		model.addAttribute("boards", boards);
		return "admin/boardlocations/missing";
	}

}
