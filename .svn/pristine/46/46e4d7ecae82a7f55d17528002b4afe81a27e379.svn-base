package com.sp.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shine.app.ReportManager;
import shine.dao.exception.ShineException;

import com.shine.boards.Report;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
@RequestMapping(value = "/admin/reports")
public class ReportsController {

	@Autowired
	private ReportManager reportManager;

	@RequestMapping(value = "/")
	@Secured(Role.ADMIN)
	public String home(Model model) throws ShineException {
		System.out.println("ReportsController /");

		List<Report> pendingReports = reportManager.getPendingReports();
		model.addAttribute("pendingReports", pendingReports);

		return "admin/reportslist";
	}

	@RequestMapping(value = "")
	@Secured(Role.ADMIN)
	public String defaultHome(Model model) throws ShineException {
		return home(model);
	}

	@RequestMapping(value = "handleReport")
	@Secured(Role.ADMIN)
	public String handleReport(@RequestParam("reportId") int reportId, @RequestParam("rationale") String rationale,
			@RequestParam(value = "decision", required = false) String acceptedStr, Model model, Principal principal)
			throws ShineException {

		if (rationale == null || rationale.trim().length() < 6) {
			model.addAttribute("error", "Rationale muat be given and at least 6 chars");
			return home(model);
		}

		if (!StringUtils.hasLength(acceptedStr)) {
			model.addAttribute("error", "The report must be either accepted or rejected");
			return home(model);
		}

		boolean accepted = "accept".equalsIgnoreCase(acceptedStr);
		int adminId = getUserId(principal);
		if (accepted) {
			System.out.println("Accepting report "+reportId);
		} else {
			System.out.println("Rejecting report " + reportId);
		}
		
		reportManager.handleReport(reportId, accepted, rationale, adminId);

		return "redirect:/mw/admin";

	}

	private int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

}
