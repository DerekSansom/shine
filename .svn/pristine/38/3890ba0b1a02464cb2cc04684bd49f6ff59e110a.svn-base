package com.sp.json.post;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import shine.app.NoticeManager;
import shine.app.ReplyManager;
import shine.dao.exception.ShineException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.shine.boards.Notice;
import com.shine.boards.Reply;
import com.sp.json.AbstractJsonController;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
public class PostController extends AbstractJsonController {

	@Autowired
	public NoticeManager noticeManager;

	@Autowired
	public ReplyManager replyManager;

	@RequestMapping(value = "/json/post/{id}", produces = "application/json")
	@ResponseBody
	public Notice getPost(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ShineException, JsonGenerationException, JsonMappingException, IOException {

		setAccessControlAllowOrigin(request, response);

		return noticeManager.getNoticeWithReplies(id);
	}

	@RequestMapping(value = "/json/reply/new", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@Secured(Role.PLAYER)
	public @ResponseBody
	Reply newReply(@RequestBody Reply jsonReply, Principal principal)
			throws ShineException, JsonGenerationException, JsonMappingException, IOException {

		jsonReply.setCreatorId(getUserId(principal));
		replyManager.createReply(jsonReply);
		return jsonReply;
	}

	private int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

}
