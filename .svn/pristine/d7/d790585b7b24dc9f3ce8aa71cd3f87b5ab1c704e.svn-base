package com.sp.mobile.post;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shine.app.NoticeManager;
import shine.app.ReportManager;
import shine.dao.exception.NotFoundException;
import shine.dao.exception.ShineException;

import com.shine.boards.Notice;
import com.shine.boards.Report;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
public class ReportController {

	private static final String CREATE_REPORT_FORM = "createreport";

	protected static Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Autowired
	private NoticeManager noticeManager;

	@Autowired
	private ReportManager reportManager;

	@Secured(Role.USER)
	@RequestMapping(value = "/post/report", method = RequestMethod.GET)
	public String reportPostScreen(@RequestParam("id") int postid, Model model) {

		Notice notice;
		try {
			notice = noticeManager.getNotice(postid);
			if (notice == null) {
				return "errors/notfound";
			}
			model.addAttribute("reportForm", new ReportForm(postid, 0, notice.getTitle()));
			return CREATE_REPORT_FORM;

		} catch (ShineException e) {
			logger.error("failed to reportscreen " + postid, e);
			return "error/error";
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/post/reportreply", method = RequestMethod.GET)
	public String reportReplyScreen(@RequestParam("id") int postid, @RequestParam("replyid") int replyid, Model model) {

		Notice notice;
		try {
			notice = noticeManager.getNotice(postid);
			if (notice == null) {
				return "errors/notfound";
			}
			model.addAttribute("reportForm", new ReportForm(postid, replyid, notice.getTitle()));
			return CREATE_REPORT_FORM;

		} catch (ShineException e) {
			logger.error("failed to report screen " + postid, e);
			return "error/error";
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/post/report", method = RequestMethod.POST)
	public String reportPost(@ModelAttribute("reportForm") ReportForm reportForm, Principal principal, Model model,
			BindingResult errors) {

		validateReport(reportForm, errors);
		if (errors.hasErrors()) {
			errors.reject(null, "please correct the errors below");
			return CREATE_REPORT_FORM;
		}

		int creatorId = getUserId(principal);
		Report report = createReport(creatorId, reportForm);
		try {
			int id = reportManager.createReport(report);
			return "redirect:reportconfirmed?noticeid=" + report.getNoticeId();

		} catch (NotFoundException e) {
			logger.warn("mw, failed to report", e);
			errors.reject(e.getMessage());
			return CREATE_REPORT_FORM;

		} catch (ShineException e) {
			logger.warn("mw, failed to report", e);
			errors.reject(e.getMessage());

			return CREATE_REPORT_FORM;
		}

	}

	private Report createReport(int creatorId, ReportForm reportForm) {
		Report report = new Report(0, creatorId, reportForm.getNoticeId(), reportForm.getReplyId(),
				reportForm.getReason());
		return report;
	}

	private void validateReport(ReportForm reportForm, BindingResult errors) {
		if (!StringUtils.hasLength(reportForm.getReason())) {
			errors.rejectValue("reason", null, "reason is required");

		}
	}

	private int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}
}
