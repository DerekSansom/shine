package shine.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.ReplyManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Reply;
import com.sp.spring.SpApplicationContext;

public class AddReplyServlet extends BasePhoneServlet {

	private ReplyManager replyManager;

	public AddReplyServlet() {
		replyManager = SpApplicationContext.getBean(ReplyManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = createReply(req);

		return "" + ret;
	}

	private int createReply(HttpServletRequest req) throws ShineException {

		String reply = req.getParameter("reply");
		int creatorId = getAuthenticatedUserId(req, "creatorid");
		int noticeId = getInt(req, "noticeid");
		if (StringUtils.isEmpty(reply) || creatorId == 0 || noticeId == 0) {
			return GeneralError.PARAM_MISSING.getCode();
		}

		Reply r = new Reply(0, noticeId, creatorId, reply);

		return replyManager.createReply(r);

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
