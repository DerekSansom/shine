package com.sp.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shine.dao.exception.ShineException;

import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
@RequestMapping(value = "/admin/boards")
public class BoardsAdminController {

	@Autowired
	private BoardsAdminManager boardsAdminManager;

	@RequestMapping(value = "/")
	@Secured(Role.ADMIN)
	public String getBoardsForm(Model model) throws ShineException {

		return "admin/boardOwnershipForm";
	}

	@RequestMapping(value = "")
	@Secured(Role.ADMIN)
	public String defaultHome(Model model) throws ShineException {
		return getBoardsForm(model);
	}

	@RequestMapping(value = "changeOwnership", method = RequestMethod.POST)
	@Secured(Role.ADMIN)
	public String populateChangeOwnership(
			Model model, Principal principal, BoardOwnershipForm boardOwnershipForm, BindingResult errors)
			throws ShineException {

		if (boardOwnershipForm.getBoardId() == null
				|| boardOwnershipForm.getOwnerId() == null) {

			model.addAttribute("error", "fill in both fields");
			return "admin/boardOwnershipForm";

		}

		int adminId = getUserId(principal);
		try {
			populateForm(boardOwnershipForm, adminId);
		} catch (SpValidationException e) {

			model.addAttribute("error", e.getMessages());
			return "admin/boardOwnershipForm";
		}

		return "admin/boardOwnershipDetails";

	}

	private void populateForm(BoardOwnershipForm boardOwnershipForm, int adminId) {

		BoardsAdminOwnershipRequest request = new BoardsAdminOwnershipRequest(boardOwnershipForm.getBoardId(),
				boardOwnershipForm.getOwnerId(), adminId);

			boardsAdminManager.populateRequest(request);
		boardOwnershipForm.setBoardName(request.getBoardName());
		boardOwnershipForm.setPreviousOwnerName(request.getPreviousOwnerName());
		boardOwnershipForm.setNewOwnerName(request.getNewOwnerName());
		boardOwnershipForm.setNewOwnerBrands(request.getNewOwnerBrands());

	}

	@RequestMapping(value = "confirmChangeOwnership", method = RequestMethod.POST)
	@Secured(Role.ADMIN)
	public String submitOwnershipChange(
			Model model, Principal principal, BoardOwnershipForm boardOwnershipForm, BindingResult errors)
			throws ShineException {

		int adminId = getUserId(principal);
		try {
			populateForm(boardOwnershipForm, adminId);
		} catch (SpValidationException e) {

		}

		try {
			boardsAdminManager.updateBoardOwnership(boardOwnershipForm.getBoardId(), boardOwnershipForm.getOwnerId(),
					boardOwnershipForm.getNewBrand());

			logChanges(adminId, boardOwnershipForm);
			model.addAttribute("boardOwnershipForm", boardOwnershipForm);
			return "admin/boardOwnershipConfirm";

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "admin/boardOwnershipDetails";

		}

	}

	private void logChanges(int adminId, BoardOwnershipForm boardOwnershipForm) {

	}

	private int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

	@ModelAttribute("boardOwnershipForm")
	public BoardOwnershipForm getBoardOwnershipForm() {
		return new BoardOwnershipForm();
	}

}
