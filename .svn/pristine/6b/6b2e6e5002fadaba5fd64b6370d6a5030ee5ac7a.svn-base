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
import shine.app.ReplyManager;
import shine.dao.exception.NotFoundException;
import shine.dao.exception.ShineException;

import com.shine.boards.Notice;
import com.shine.boards.Reply;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
public class ReplyController {

	private static final String CREATE_REPLY_FORM = "createreply";

	protected static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private NoticeManager noticeManager;

	@Autowired
	private ReplyManager replyManager;

	@Secured(Role.USER)
	@RequestMapping(value = "/post/reply", method = RequestMethod.GET)
	public String newReplyScreen(@RequestParam("id") int postid, Model model) {

		Notice notice;
		try {
			notice = noticeManager.getNotice(postid);
			if (notice == null) {
				return "errors/notfound";
			}
			model.addAttribute("replyForm", new ReplyForm(postid, notice.getBoardId(), notice.getTitle()));
			return CREATE_REPLY_FORM;

		} catch (ShineException e) {
			logger.error("failed to reply screen " + postid, e);
			return "error/error";
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/post/reply", method = RequestMethod.POST)
	public String replyToPost(@ModelAttribute("replyForm") ReplyForm replyForm, Principal principal, Model model,
			BindingResult errors) {

		validateReply(replyForm, errors);
		if (errors.hasErrors()) {
			errors.reject(null, "please correct the errors below");
			return CREATE_REPLY_FORM;
		}

		int creatorId = getUserId(principal);
		Reply reply = createReply(creatorId, replyForm);
		try {
			int id = replyManager.createReply(reply);
			return "redirect:replyconfirmed?noticeid=" + reply.getNoticeId();

		} catch (NotFoundException e) {
			logger.warn("mw, failed to reply", e);
			errors.reject(e.getMessage());
			return CREATE_REPLY_FORM;

		} catch (ShineException e) {
			logger.warn("mw, failed to reply", e);
			errors.reject(e.getMessage());

			return CREATE_REPLY_FORM;
		}

	}

	private Reply createReply(int creatorId, ReplyForm replyForm) {
		Reply reply = new Reply(0, replyForm.getNoticeId(), creatorId, replyForm.getReply());
		return reply;
	}

	private void validateReply(ReplyForm reply, BindingResult errors) {
		if (!StringUtils.hasLength(reply.getReply())) {
			errors.rejectValue("reply", null, "reply is required");

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
