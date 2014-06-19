package com.sp.mobile.post;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shine.app.BoardManager;
import shine.app.NoticeManager;
import shine.app.utils.ShineProperties;
import shine.dao.exception.NotFoundException;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Notice;
import com.sp.img.ImageScaler;
import com.sp.mobile.AbstractController;
import com.sp.security.Role;

@Controller
public class NoticeController extends AbstractController {

	private static final String CREATE_POST_FORM = "createpostform2";
	static final String VIEW_POST = "postview";
//	private static final String CREATE_POST_FORM = "createpostlite";

	protected static Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeManager noticeManager;

	@Autowired
	private BoardManager boardManager;

	@Autowired
	private ImageScaler imageScaler;

	@RequestMapping(value = "/post")
	public String viewPostScreen(@RequestParam("id") int postid, Model model) {

		try {
			Notice post = noticeManager.getNoticeWithReplies(postid);
			model.addAttribute("post", post);

			populatePositionData(post, model);

			return VIEW_POST;
		} catch (ShineException e) {
			if (e.getCode() == GeneralError.NOT_FOUND.getCode()) {
				return "errors/notfound";
			}
			logger.error("failed to viewPost screen " + postid, e);
			return "errors/error";
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/addpost")
	public String newPostScreen(@RequestParam("id") int boardid, Model model) {

		model.addAttribute("newPost", new PostForm(boardid));
		return CREATE_POST_FORM;

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/post/submit", method = RequestMethod.POST)
	public String createPost(@ModelAttribute("newPost") PostForm newPost, Principal principal,
			Model model, BindingResult errors) {

		validateNotice(newPost, errors);
		if (errors.hasErrors()) {
			errors.reject(null, "please correct following errors");
			return CREATE_POST_FORM;
		}

		int creatorId = getUserId(principal);
		Notice newNotice = createNotice(creatorId, newPost);
		try {
			int id;
			MultipartFile imageUpload = newPost.getImageUpload();
			InputStream is = null;
			if (imageUpload != null) {
				is = imageUpload.getInputStream();

			}
			if (is != null) {
				
				BufferedImage bufferedImage = imageScaler.scaleImage(is, ShineProperties.maxImageDimension());
				id = noticeManager.createNoticeWithImage(newNotice, bufferedImage);
			}
			else {
				id = noticeManager.createNotice(newNotice);
			}
			model.addAttribute("bid", newPost.getBid());
			return "redirect:../post?id=" + id;

		} catch (NotFoundException e) {
			logger.warn("mw, failed to create notice", e);
			errors.reject(e.getMessage());
			return CREATE_POST_FORM;

		} catch (ShineException e) {
			logger.warn("mw, failed to create notice", e);
			errors.reject(e.getMessage());

			return CREATE_POST_FORM;
		} catch (IOException e) {
			errors.reject("imageUpload");
			return CREATE_POST_FORM;
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/post/replyconfirmed", method = RequestMethod.GET)
	public String confirmReply(@RequestParam("noticeid") int postid, Model model) {

		try {
			Notice post = noticeManager.getNoticeWithReplies(postid);
			model.addAttribute("post", post);

			populatePositionData(post, model);

			model.addAttribute("msg", "your reply is below");
			return NoticeController.VIEW_POST;

		} catch (ShineException e) {
			logger.error("failed to reply screen " + postid, e);
			return "error/error";
		}

	}

	@Secured(Role.USER)
	@RequestMapping(value = "/post/reportconfirmed", method = RequestMethod.GET)
	public String confirmReport(@RequestParam("noticeid") int postid, Model model) {

		try {
			Notice post = noticeManager.getNoticeWithReplies(postid);
			model.addAttribute("post", post);

			populatePositionData(post, model);

			model.addAttribute("msg", "your report has been submitted and will be dealt with, thank you");
			model.addAttribute("post", post);
			return NoticeController.VIEW_POST;

		} catch (ShineException e) {
			logger.error("failed to reply screen " + postid, e);
			return "error/error";
		}

	}

	private byte[] getImagebytes(MultipartFile imageUpload) throws IOException {
		byte[] bytes = imageUpload.getBytes();
		return bytes;
	}

	private void validateNotice(PostForm newPost, BindingResult errors) {
		int boardId = newPost.getBid();
		if (boardId < 1) {
			errors.reject(GeneralError.NOT_FOUND.name());
			return;
		}

		if (!StringUtils.hasText(newPost.getTitle())) {
			errors.rejectValue("title", null, "title is required");
		}
	}

	@RequestMapping(value = "/addpost2")
	public String createPost2() {

		return CREATE_POST_FORM;

	}

	private void populatePositionData(Notice post, Model model) {

		List<Notice> notices = noticeManager.getNotices(post.getBoardId(), 0, 0, 0, false);

		int position = 0;
		for (Notice notice : notices) {
			position++;
			if (notice.getId() == post.getId()) {
				break;
			}

		}

		model.addAttribute("position", position);
		model.addAttribute("total", notices.size());
		if (position > 1) {
			model.addAttribute("previous", notices.get(position - 2).getId());
		}
		if (position < notices.size()) {
			model.addAttribute("next", notices.get(position).getId());
		}

	}

	private Notice createNotice(int creatorId, PostForm newPost) {

		DateTime expiry = getExpiry(newPost.getExpires());

		Notice notice = new Notice(newPost.getBid(), creatorId, newPost.getTitle(), newPost.getNotice(),
				newPost.getCatid(), expiry.toDate());
		return notice;
	}

	private DateTime getExpiry(String expires) {

		DateTime now = DateTime.now();
		switch (expires) {
		case "Tonight":
			DateMidnight midnight = new DateMidnight();
			now = midnight.toDateTime();
		case "24 hours":
			now = now.plusDays(1);
			break;
		case "1 week":
			now = now.plusWeeks(1);
			break;
		case "1 month":
			now = now.plusMonths(1);
			break;
		case "3 months":
		default:
			now = now.plusMonths(3);
			break;
		}

		return now;
	}


}
